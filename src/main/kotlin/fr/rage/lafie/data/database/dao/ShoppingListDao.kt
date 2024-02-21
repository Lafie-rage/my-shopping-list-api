package fr.rage.lafie.data.database.dao

import fr.rage.lafie.data.database.entity.ShoppingListEntity
import org.jetbrains.exposed.sql.Database

class ShoppingListDao(
    database: Database,
) : BaseDao<ShoppingListEntity>(
    entityClass = ShoppingListEntity,
    database = database,
) {
    suspend fun create(): ShoppingListEntity = runDbQuery {
        ShoppingListEntity.new {
        }
    }

}