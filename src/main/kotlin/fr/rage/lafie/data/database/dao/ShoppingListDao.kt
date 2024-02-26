package fr.rage.lafie.data.database.dao

import fr.rage.lafie.data.database.entity.ShoppingListEntity
import org.jetbrains.exposed.sql.Database
import java.util.*

class ShoppingListDao(
    database: Database,
) : BaseDao<ShoppingListEntity>(
    entityClass = ShoppingListEntity,
    database = database,
) {
    suspend fun create(id: UUID): ShoppingListEntity = runDbQuery {
        ShoppingListEntity.new(id) {
        }
    }

}