package fr.rage.lafie.data.database.dao

import fr.rage.lafie.data.database.entity.ShoppingItemEntity
import fr.rage.lafie.data.database.entity.ShoppingListEntity
import fr.rage.lafie.data.database.table.ShoppingItemTable
import org.jetbrains.exposed.sql.Database
import java.util.*

class ShoppingItemDao(
    database: Database,
) : BaseDao<ShoppingItemEntity>(
    entityClass = ShoppingItemEntity,
    database = database,
) {
    suspend fun create(
        label: String,
        count: Float,
        unit: String,
        shoppingList: ShoppingListEntity,
    ): ShoppingItemEntity = runDbQuery {
        ShoppingItemEntity.new {
            this.label = label
            this.unit = unit
            this.count = count
            this.shoppingListId = shoppingList.id
        }
    }


    suspend fun getByShoppingListId(shoppingListId: UUID): List<ShoppingItemEntity> = runDbQuery {
        ShoppingItemEntity.find {
            ShoppingItemTable.shoppingListId eq shoppingListId
        }.toList()
    }
}