package fr.rage.lafie.data.database.dao

import fr.rage.lafie.data.database.entity.ShoppingListEntity
import org.jetbrains.exposed.sql.Database

class ShoppingListDao(
    database: Database,
): BaseDao<ShoppingListEntity>(
    entityClass = ShoppingListEntity,
    database = database,
) {
    override suspend fun create(item: ShoppingListEntity): ShoppingListEntity = runDbQuery {
        ShoppingListEntity.new {
        }
    }

}