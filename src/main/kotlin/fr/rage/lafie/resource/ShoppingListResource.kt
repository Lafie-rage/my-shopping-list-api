package fr.rage.lafie.resource

import fr.rage.lafie.utils.UUIDSerializer
import io.ktor.resources.*
import kotlinx.serialization.Serializable
import java.util.*

@Resource("shopping-lists")
class ShoppingLists {

    @Resource("{id}")
    class ById(
        @Serializable(with = UUIDSerializer::class)
        val id: UUID,
    )

    @Resource("")
    class Create
}