package fr.rage.lafie.exception.not.found

import fr.rage.lafie.exception.common.NotFoundException
import java.util.*

class ShoppingItemNotFoundException(
    id: UUID,
) : NotFoundException(
    message = "Unable to find a shopping item with the ID '$id'",
    errorCode = "404_002",
)