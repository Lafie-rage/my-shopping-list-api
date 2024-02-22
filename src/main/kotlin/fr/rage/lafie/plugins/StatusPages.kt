package fr.rage.lafie.plugins

import fr.rage.lafie.dto.response.ErrorMessage
import fr.rage.lafie.exception.common.HttpException
import io.ktor.server.application.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*

fun Application.configureStatusPages() {
    install(StatusPages) {
        exception<HttpException> { call, cause ->
            val errorMessage = ErrorMessage(
                code = cause.errorCode,
                reason = cause.message!!,
                stackTrace = cause.stackTrace.map { it.toString() },
            )
            call.respond(cause.status, errorMessage)
        }
    }
}