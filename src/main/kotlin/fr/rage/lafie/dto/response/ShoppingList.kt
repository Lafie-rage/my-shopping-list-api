package fr.rage.lafie.dto.response

import java.util.*

data class ShoppingList(
    val id: UUID,
    val items: List<ShoppingItem>,
)