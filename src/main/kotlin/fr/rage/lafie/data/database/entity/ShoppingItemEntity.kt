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
    var shoppingListId by ShoppingItemTable.shoppingListId

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ShoppingItemEntity

        if (id != other.id) return false
        if (label != other.label) return false
        if (count != other.count) return false
        if (unit != other.unit) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + label.hashCode()
        result = 31 * result + count.hashCode()
        result = 31 * result + unit.hashCode()
        return result
    }


}