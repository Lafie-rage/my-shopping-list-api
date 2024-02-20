package fr.lafie.rage.data.database.dao

import fr.rage.lafie.data.database.dao.BaseDao
import kotlinx.coroutines.test.runTest
import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.transactions.transaction
import kotlin.test.*

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

    @BeforeTest
    fun setUpSchemas() {
        transaction(database) {
            SchemaUtils.create(*tables)
        }
    }

    @Test
    fun `try to create an item then search it expecting it to exist`() {
        val item = `create an item to test a retrieve function`()
        transaction(database) { assertNotNull(entityClass.findById(item.id.value)) }
    }

    @Test
    fun `try to get an item by its ID after inserting it excepting to be found`() = runTest {
        val item = `create an item to test a retrieve function`()
        val result = dao.getById(item.id.value)
        assertEquals(item, result)
    }

    @Test
    fun `try to delete an item by its ID after inserting it then search it excepting it to be deleted`() = runTest {
        val item = `create an item to test a retrieve function`()
        val itemId = item.id.value
        transaction(database) {
            assertNotNull(entityClass.findById(itemId))
        }
        dao.delete(itemId)
        transaction(database) {
            assertNull(entityClass.findById(itemId))
        }
    }

    protected abstract fun `create an item to test a retrieve function`(): E
}