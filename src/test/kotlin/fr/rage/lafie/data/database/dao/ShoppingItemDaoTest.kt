package fr.rage.lafie.data.database.dao

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isNotNull
import fr.rage.lafie.data.database.entity.ShoppingItemEntity
import fr.rage.lafie.data.database.entity.ShoppingListEntity
import fr.rage.lafie.data.database.table.ShoppingItemTable
import fr.rage.lafie.data.database.table.ShoppingListTable
import kotlinx.coroutines.test.runTest
import org.jetbrains.exposed.sql.transactions.transaction
import org.junit.jupiter.api.Test

class ShoppingItemDaoTest : BaseDaoTest<ShoppingItemDao, ShoppingItemEntity>(
    entityClass = ShoppingItemEntity,
    tables = arrayOf(ShoppingItemTable, ShoppingListTable)
) {
    override val dao: ShoppingItemDao
        get() = ShoppingItemDao(database)

    override fun `create an item to test a retrieve function`(): ShoppingItemEntity = transaction(database) {
        val shoppingList = ShoppingListEntity.new {}
        return@transaction ShoppingItemEntity.new {
            this.label = "label"
            this.count = 0f
            this.unit = "unit"
            this.shoppingListId = shoppingList.id
        }
    }

    @Test
    fun `update an item and check if the update has been correctly proceeded`() = runTest {
        val shoppingItem = `create an item to test a retrieve function`()

        val updatedItem = dao.update(
            id = shoppingItem.id.value,
            label = "New label",
            count = shoppingItem.count,
            unit = shoppingItem.unit,
        )

        assertThat(updatedItem).isNotNull()
        assertThat(updatedItem!!.label).isNotNull().isEqualTo("New label")
    }
}