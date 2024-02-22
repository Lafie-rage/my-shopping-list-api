package fr.rage.lafie.exception.not.found

import fr.rage.lafie.exception.common.NotFoundException
import java.util.*

class ShoppingListNotFoundException(
    id: UUID,
) : NotFoundException(
    message = "Unable to find a shopping list with the ID '$id'",
    errorCode = "404_001"
)