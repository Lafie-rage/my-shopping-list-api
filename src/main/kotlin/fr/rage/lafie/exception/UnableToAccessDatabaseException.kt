package fr.rage.lafie.exception

import fr.rage.lafie.exception.common.InternalServerErrorException

class UnableToAccessDatabaseException(
    reason: String,
) : InternalServerErrorException(
    message = "Unable to establish a connection with the database : $reason",
    errorCode = "500_001",
)