package fr.rage.lafie.plugins.routing

import fr.rage.lafie.dto.response.ShoppingList
import fr.rage.lafie.exception.not.found.ShoppingListNotFoundException
import fr.rage.lafie.resource.ShoppingLists
import fr.rage.lafie.service.ShoppingListService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.resources.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Routing.configureShoppingListRouting() {
    val service by inject<ShoppingListService>()

    getShoppingListById(service)
}

private fun Route.getShoppingListById(service: ShoppingListService) {
    get<ShoppingLists.ById> { params ->
        val id = params.id

        val shoppingList: ShoppingList = service.getByIdOrCreate(id)

        call.respond(
            status = HttpStatusCode.OK,
            message = shoppingList,
        )
    }
}