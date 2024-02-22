package fr.rage.lafie.data.database.dao

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isNotNull
import assertk.assertions.isNull
import kotlinx.coroutines.test.runTest
import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.transactions.transaction
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

abstract class BaseDaoTest<D : BaseDao<E>, E : UUIDEntity>(
    private val entityClass: UUIDEntityClass<E>,
    private val tables: Array<Table>,
) {
    protected abstract val dao: D
    protected val database = Database.connect(
        url = "jdbc:h2:mem:unit-test;DB_CLOSE_DELAY=-1",
        user = "root",
        driver = "org.h2.Driver",
        password = ""
    )

    @BeforeEach
    fun setUpSchemas() {
        transaction(database) {
            SchemaUtils.create(*tables)
        }
    }

    @Test
    fun `try to create an item then search it expecting it to exist`() {
        val item = `create an item to test a retrieve function`()
        transaction(database) { assertThat(entityClass.findById(item.id.value)).isNotNull() }
    }

    @Test
    fun `try to get an item by its ID after inserting it excepting to be found`() = runTest {
        val item = `create an item to test a retrieve function`()
        val result = dao.getById(item.id.value)
        assertThat(result).isNotNull().isEqualTo(item)
    }

    @Test
    fun `try to delete an item by its ID after inserting it then search it excepting it to be deleted`() = runTest {
        val item = `create an item to test a retrieve function`()
        val itemId = item.id.value
        transaction(database) {
            assertThat(entityClass.findById(itemId)).isNotNull()
        }
        dao.delete(itemId)
        transaction(database) {
            assertThat(entityClass.findById(itemId)).isNull()
        }
    }

    protected abstract fun `create an item to test a retrieve function`(): E
}