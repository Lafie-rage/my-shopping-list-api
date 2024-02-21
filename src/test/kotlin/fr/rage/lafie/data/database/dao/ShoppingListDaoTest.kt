package fr.rage.lafie.data.database.dao

import fr.rage.lafie.data.database.entity.ShoppingListEntity
import fr.rage.lafie.data.database.table.ShoppingListTable
import org.jetbrains.exposed.sql.transactions.transaction

class ShoppingListDaoTest : BaseDaoTest<ShoppingListDao, ShoppingListEntity>(
    entityClass = ShoppingListEntity,
    tables = arrayOf(ShoppingListTable)
) {
    override val dao: ShoppingListDao
        get() = ShoppingListDao(database)

    override fun `create an item to test a retrieve function`(): ShoppingListEntity =
        transaction(database) { ShoppingListEntity.new {} }
}