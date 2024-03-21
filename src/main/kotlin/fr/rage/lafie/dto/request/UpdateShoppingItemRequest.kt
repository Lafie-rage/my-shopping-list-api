package fr.rage.lafie.dto.request

import fr.rage.lafie.dto.BaseShoppingItem
import kotlinx.serialization.Serializable

@Serializable
data class UpdateShoppingItemRequest(
    override val label: String,
    override val count: Float,
    override val unit: String,
) : BaseShoppingItem()
