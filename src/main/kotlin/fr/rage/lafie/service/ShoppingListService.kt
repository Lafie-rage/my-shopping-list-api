package fr.rage.lafie.service

import fr.rage.lafie.dto.response.ShoppingList
import fr.rage.lafie.mapper.toDto
import fr.rage.lafie.repository.ShoppingListRepository
import java.util.*

class ShoppingListService(
    private val repository: ShoppingListRepository,
    private val shoppingListService: ShoppingItemService,
) {
    suspend fun getByIdOrCreate(id: UUID): ShoppingList {
        val shoppingListEntity = repository.getByIdOrCreate(id)
        return shoppingListEntity.toDto(shoppingListService.getByShoppingListId(id))
    }
}