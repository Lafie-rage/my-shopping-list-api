package fr.rage.lafie.mapper

import fr.rage.lafie.data.database.entity.ShoppingListEntity
import fr.rage.lafie.dto.response.ShoppingList

fun ShoppingListEntity.toDto() = ShoppingList(
    id = this.id.value,
    items = items.map { it.toDto() }
)