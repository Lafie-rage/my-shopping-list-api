package fr.rage.lafie.data.database.table

import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.sql.ReferenceOption

object ShoppingItemTable : UUIDTable() {
    val label = varchar("label", 255)
    val count = float("count")
    val unit = varchar("unit", 64)
    val shoppingListId = reference(
        "shopping-list-id",
        ShoppingListTable,
        onUpdate = ReferenceOption.CASCADE,
        onDelete = ReferenceOption.CASCADE,
    )
}