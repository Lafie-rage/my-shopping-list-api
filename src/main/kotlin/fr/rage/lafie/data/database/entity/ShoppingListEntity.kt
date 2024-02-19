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
}