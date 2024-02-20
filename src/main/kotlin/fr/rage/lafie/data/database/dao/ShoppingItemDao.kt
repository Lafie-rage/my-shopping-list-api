package fr.rage.lafie.data.database.dao

import fr.rage.lafie.data.database.entity.ShoppingItemEntity
import org.jetbrains.exposed.sql.Database

class ShoppingItemDao(
    database: Database,
) : BaseDao<ShoppingItemEntity>(
    entityClass = ShoppingItemEntity,
    database = database,
) {
    override suspend fun create(item: ShoppingItemEntity): ShoppingItemEntity = runDbQuery {
        ShoppingItemEntity.new {
            this.label = item.label
            this.unit = item.unit
            this.count = item.count
            this.shoppingList = item.shoppingList
        }
    }

}