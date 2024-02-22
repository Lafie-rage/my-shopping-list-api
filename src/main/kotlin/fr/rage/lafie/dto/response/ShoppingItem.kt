package fr.rage.lafie.dto.response

import fr.rage.lafie.dto.BaseShoppingItem
import fr.rage.lafie.utils.UUIDSerializer
import kotlinx.serialization.Serializable
import java.util.*

@Serializable
data class ShoppingItem(
    @Serializable(with = UUIDSerializer::class)
    val id: UUID,
    override val label: String,
    override val count: Float,
    override val unit: String,
) : BaseShoppingItem()