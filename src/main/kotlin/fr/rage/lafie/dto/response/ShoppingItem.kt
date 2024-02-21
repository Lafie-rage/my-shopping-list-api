package fr.rage.lafie.dto.response

import fr.rage.lafie.dto.BaseShoppingItem
import java.util.*

data class ShoppingItem(
    val id: UUID,
    override val label: String,
    override val count: Float,
    override val unit: String,
) : BaseShoppingItem(label, count, unit)