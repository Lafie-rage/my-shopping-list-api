package fr.rage.lafie.data.database.entity

import fr.rage.lafie.data.database.table.ShoppingListTable
import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import java.util.*

class ShoppingListEntity(id: EntityID<UUID>) : UUIDEntity(id) {
    companion object : UUIDEntityClass<ShoppingListEntity>(ShoppingListTable)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ShoppingListEntity

        return id == other.id
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }


}