package fr.rage.lafie.data.database.entity

import fr.rage.lafie.data.database.table.ShoppingItemTable
import fr.rage.lafie.data.database.table.ShoppingListTable
import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.SizedIterable
import java.util.*

class ShoppingListEntity(id: EntityID<UUID>) : UUIDEntity(id) {
    companion object : UUIDEntityClass<ShoppingListEntity>(ShoppingListTable)

    val items: SizedIterable<ShoppingItemEntity> by ShoppingItemEntity referrersOn ShoppingItemTable.shoppingListId
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ShoppingListEntity

        if (id != other.id) return false
        if (items != other.items) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + items.hashCode()
        return result
    }


}