package fr.rage.lafie.exception.common

import io.ktor.http.*

abstract class NotFoundException(
    message: String,
    errorCode: String,
) : HttpException(
    message = message,
    status = HttpStatusCode.NotFound,
    errorCode = errorCode,
)