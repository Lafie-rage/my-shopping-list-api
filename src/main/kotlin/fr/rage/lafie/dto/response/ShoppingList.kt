package fr.rage.lafie.dto.response

import fr.rage.lafie.utils.UUIDSerializer
import kotlinx.serialization.Serializable
import java.util.*

@Serializable
data class ShoppingList(
    @Serializable(with = UUIDSerializer::class)
    val id: UUID,
    val items: List<ShoppingItem>,
)