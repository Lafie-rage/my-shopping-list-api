package fr.rage.lafie.data.database.entity

import fr.rage.lafie.data.database.table.ShoppingItemTable
import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import java.util.*

class ShoppingItemEntity(id: EntityID<UUID>) : UUIDEntity(id) {
    companion object : UUIDEntityClass<ShoppingItemEntity>(ShoppingItemTable)

    var label by ShoppingItemTable.label
    var count by ShoppingItemTable.count
    var unit by ShoppingItemTable.unit
    var shoppingList by ShoppingListEntity referencedOn ShoppingItemTable.shoppingListId
}