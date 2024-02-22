package fr.rage.lafie

import fr.rage.lafie.plugins.configureDatabases
import fr.rage.lafie.plugins.configureRequestValidation
import fr.rage.lafie.plugins.configureSerialization
import fr.rage.lafie.plugins.di.configureKoin
import fr.rage.lafie.plugins.routing.configureRouting
import io.ktor.server.application.*

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    // Init DB before Koin because DAO module needs to know the DB instance
    configureDatabases()
    configureKoin()
    configureSerialization()
    configureRequestValidation()
    configureRouting()
}
