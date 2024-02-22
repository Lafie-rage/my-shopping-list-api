package fr.rage.lafie.plugins

import fr.rage.lafie.data.database.table.ShoppingItemTable
import fr.rage.lafie.data.database.table.ShoppingListTable
import fr.rage.lafie.exception.UnableToAccessDatabaseException
import io.ktor.server.application.*
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

fun Application.configureDatabases() {
    val host: String = environment.config.propertyOrNull("database.host")?.getString()
        ?: throw UnableToAccessDatabaseException("No host defined")
    val username: String = environment.config.propertyOrNull("database.username")?.getString()
        ?: throw UnableToAccessDatabaseException("No database username defined")
    val password: String = environment.config.propertyOrNull("database.password")?.getString()
        ?: throw UnableToAccessDatabaseException("No database password defined")
    val engine: String = environment.config.propertyOrNull("database.engine")?.getString()
        ?: throw UnableToAccessDatabaseException("No database engine defined")
    val driver: String = environment.config.propertyOrNull("database.driver")?.getString()
        ?: throw UnableToAccessDatabaseException("No database driver defined")

    val database = Database.connect(
        url = "jdbc:$engine:$host",
        user = username,
        driver = driver,
        password = password
    )

    initSchemas(database)
}

private fun initSchemas(database: Database) = transaction(database) {
    val tables = arrayOf(
        ShoppingItemTable,
        ShoppingListTable,
    )

    SchemaUtils.create(*tables)
}
