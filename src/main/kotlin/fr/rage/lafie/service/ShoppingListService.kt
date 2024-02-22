package fr.rage.lafie.service

import fr.rage.lafie.dto.response.ShoppingList
import fr.rage.lafie.mapper.toDto
import fr.rage.lafie.repository.ShoppingListRepository
import java.util.*

class ShoppingListService(
    private val repository: ShoppingListRepository,
) {
    suspend fun getById(id: UUID): ShoppingList? = repository.getById(id)?.toDto()

    suspend fun create(): ShoppingList = repository.create().toDto()
}