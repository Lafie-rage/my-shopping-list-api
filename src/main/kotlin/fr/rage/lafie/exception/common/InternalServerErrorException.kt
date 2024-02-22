package fr.rage.lafie.exception.common

import io.ktor.http.*

abstract class InternalServerErrorException(
    message: String,
    errorCode: String,
) : HttpException(
    message = message,
    status = HttpStatusCode.InternalServerError,
    errorCode = errorCode,
)