package fr.rage.lafie.repository

import fr.rage.lafie.data.database.dao.ShoppingItemDao
import fr.rage.lafie.data.database.entity.ShoppingItemEntity
import fr.rage.lafie.data.database.entity.ShoppingListEntity
import java.util.*

class ShoppingItemRepository(
    private val dao: ShoppingItemDao,
) {
    suspend fun create(
        label: String,
        count: Float,
        unit: String,
        shoppingList: ShoppingListEntity,
    ): ShoppingItemEntity = dao.create(
        label = label,
        count = count,
        unit = unit,
        shoppingList = shoppingList,
    )

    suspend fun getById(id: UUID): ShoppingItemEntity? = dao.getById(id)
}