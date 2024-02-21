package fr.rage.lafie.dto.request

import fr.rage.lafie.dto.BaseShoppingItem

data class ShoppingItemToCreate(
    override val label: String,
    override val count: Float,
    override val unit: String,
) : BaseShoppingItem(label, count, unit)
