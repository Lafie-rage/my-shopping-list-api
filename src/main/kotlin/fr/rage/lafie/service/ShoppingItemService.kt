package fr.rage.lafie.service

import fr.rage.lafie.dto.request.ShoppingItemToCreate
import fr.rage.lafie.dto.response.ShoppingItem
import fr.rage.lafie.exception.not.found.ShoppingListNotFoundException
import fr.rage.lafie.mapper.toDto
import fr.rage.lafie.repository.ShoppingItemRepository
import fr.rage.lafie.repository.ShoppingListRepository
import java.util.*

class ShoppingItemService(
    private val repository: ShoppingItemRepository,
    private val shoppingListRepository: ShoppingListRepository,
) {
    suspend fun createItemOnListShoppingList(
        shoppingListId: UUID,
        shoppingItem: ShoppingItemToCreate,
    ): ShoppingItem {
        val shoppingList = shoppingListRepository.getById(shoppingListId)
            ?: throw ShoppingListNotFoundException(shoppingListId)
        return repository.create(
            label = shoppingItem.label,
            count = shoppingItem.count,
            unit = shoppingItem.unit,
            shoppingList = shoppingList,
        ).toDto()
    }

    suspend fun getById(id: UUID): ShoppingItem? = repository.getById(id)?.toDto()
}