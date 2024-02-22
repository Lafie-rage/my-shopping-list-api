package fr.rage.lafie.exception.common

import io.ktor.http.*

abstract class HttpException(
    message: String,
    val status: HttpStatusCode,
    val errorCode: String,
) : RuntimeException(message)