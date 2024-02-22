package fr.rage.lafie.repository

import fr.rage.lafie.data.database.dao.ShoppingListDao
import fr.rage.lafie.data.database.entity.ShoppingListEntity
import java.util.*

class ShoppingListRepository(
    private val dao: ShoppingListDao,
) {
    suspend fun getById(id: UUID): ShoppingListEntity? = dao.getById(id)

    suspend fun create(): ShoppingListEntity = dao.create()
}