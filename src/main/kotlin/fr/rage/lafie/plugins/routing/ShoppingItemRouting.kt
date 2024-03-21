package fr.rage.lafie.plugins.routing

import fr.rage.lafie.dto.request.ShoppingItemToCreate
import fr.rage.lafie.dto.request.UpdateShoppingItemRequest
import fr.rage.lafie.exception.not.found.ShoppingItemNotFoundException
import fr.rage.lafie.resource.ShoppingItems
import fr.rage.lafie.service.ShoppingItemService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.resources.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Routing.configureShoppingItemRouting() {
    val service by inject<ShoppingItemService>()

    createShoppingItem(service)
    getShoppingItemById(service)
    updateShoppingItem(service)
    deleteShoppingItem(service)
}

private fun Route.createShoppingItem(service: ShoppingItemService) {
    post<ShoppingItems.AddOnShoppingList, ShoppingItemToCreate> { params, shoppingItemToCreate ->
        val shoppingListId = params.shoppingListId

        val shoppingItem = service.createItemOnListShoppingList(
            shoppingListId,
            shoppingItemToCreate
        )

        call.respond(
            status = HttpStatusCode.Created,
            message = shoppingItem,
        )
    }
}

private fun Route.getShoppingItemById(service: ShoppingItemService) {
    get<ShoppingItems.ById> { params ->
        val id = params.id
        val shoppingItem = service.getById(id) ?: throw ShoppingItemNotFoundException(id)
        call.respond(
            status = HttpStatusCode.OK,
            message = shoppingItem
        )
    }
}

private fun Route.updateShoppingItem(service: ShoppingItemService) {
    put<ShoppingItems.ById, UpdateShoppingItemRequest> {params, shoppingItem ->
        val response = service.update(params.id, shoppingItem) ?: throw ShoppingItemNotFoundException(id = params.id)

        call.respond(
            status = HttpStatusCode.OK ,
            message = response
        )
    }
}

private fun Route.deleteShoppingItem(service: ShoppingItemService) {
    delete<ShoppingItems.ById> {params ->
        service.deleteById(params.id)

        call.respond(
            status = HttpStatusCode.NoContent,
            message = "Item deleted.",
        )
    }
}