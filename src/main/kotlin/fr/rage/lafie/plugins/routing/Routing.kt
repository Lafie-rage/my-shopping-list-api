package fr.rage.lafie.plugins.routing

import io.ktor.server.application.*
import io.ktor.server.resources.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    install(Resources)
    routing {
        configureShoppingItemRouting()
        configureShoppingListRouting()
    }
}
