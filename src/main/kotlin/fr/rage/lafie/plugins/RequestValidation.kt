package fr.rage.lafie.plugins

import fr.rage.lafie.dto.request.ShoppingItemToCreate
import io.ktor.server.application.*
import io.ktor.server.plugins.requestvalidation.*

fun Application.configureRequestValidation() {
    install(RequestValidation) {
        validate<ShoppingItemToCreate> { shoppingItemToCreate ->
            with(shoppingItemToCreate) {
                when {
                    label.isBlank() -> ValidationResult.Invalid("The shopping item label cannot be blank.")
                    count <= 0 -> ValidationResult.Invalid("The shopping item count should be greater than 0.")
                    unit.isBlank() -> ValidationResult.Invalid("The shopping item unit cannot be blank.")
                    else -> ValidationResult.Valid
                }

            }
        }

    }
}