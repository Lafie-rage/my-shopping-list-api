package fr.rage.lafie.mapper

import fr.rage.lafie.data.database.entity.ShoppingItemEntity
import fr.rage.lafie.dto.response.ShoppingItem

fun ShoppingItemEntity.toDto() = ShoppingItem(
    id = this.id.value,
    label = this.label,
    count = this.count,
    unit = this.unit,
)