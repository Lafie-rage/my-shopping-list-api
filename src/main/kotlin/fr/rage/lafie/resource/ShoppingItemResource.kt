package fr.rage.lafie.resource

import fr.rage.lafie.utils.UUIDSerializer
import io.ktor.resources.*
import kotlinx.serialization.Serializable
import java.util.*

@Resource("shopping-items")
class ShoppingItems {

    @Resource("{id}")
    class ById(
        val parent: ShoppingItems = ShoppingItems(),
        @Serializable(with = UUIDSerializer::class)
        val id: UUID,
    )

    @Resource("")
    class AddOnShoppingList(
        val parent: ShoppingItems = ShoppingItems(),
        @Serializable(with = UUIDSerializer::class)
        val shoppingListId: UUID,
    )
}