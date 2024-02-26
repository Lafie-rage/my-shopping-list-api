package fr.rage.lafie.mapper

import fr.rage.lafie.data.database.entity.ShoppingListEntity
import fr.rage.lafie.dto.response.ShoppingItem
import fr.rage.lafie.dto.response.ShoppingList

fun ShoppingListEntity.toDto(items: List<ShoppingItem>): ShoppingList {
    return ShoppingList(
        id = this.id.value,
        items = items
    )
}