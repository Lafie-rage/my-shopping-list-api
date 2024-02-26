package fr.rage.lafie.data.database.dao

import fr.rage.lafie.data.database.entity.ShoppingItemEntity
import fr.rage.lafie.data.database.entity.ShoppingListEntity
import fr.rage.lafie.data.database.table.ShoppingItemTable
import fr.rage.lafie.data.database.table.ShoppingListTable
import org.jetbrains.exposed.sql.transactions.transaction

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
}