package fr.rage.lafie.plugins.routing

import fr.rage.lafie.exception.not.found.ShoppingListNotFoundException
import fr.rage.lafie.resource.ShoppingLists
import fr.rage.lafie.service.ShoppingListService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.resources.*
import io.ktor.server.response.*
import io.ktor.server.routing.Route
import io.ktor.server.routing.Routing
import org.koin.ktor.ext.inject

fun Routing.configureShoppingListRouting() {
    val service by inject<ShoppingListService>()
    
    getShoppingListById(service)
    createShoppingList(service)
}

private fun Route.getShoppingListById(service: ShoppingListService) {
    get<ShoppingLists.ById> { params ->
        val id = params.id

        val shoppingList = service.getById(id) ?: throw ShoppingListNotFoundException(id)

        call.respond(
            status = HttpStatusCode.OK,
            message = shoppingList,
        )
    }
}

private fun Route.createShoppingList(service: ShoppingListService) {
    post<ShoppingLists.Create> {
        val shoppingList = service.create()

        call.respond(
            status = HttpStatusCode.Created,
            message = shoppingList
        )
    }
}