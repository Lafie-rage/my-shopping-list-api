package fr.rage.lafie.plugins

import fr.rage.lafie.data.database.table.ShoppingItemTable
import fr.rage.lafie.data.database.table.ShoppingListTable
import fr.rage.lafie.exception.UnableToAccessDatabaseException
import io.ktor.server.application.*
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import org.postgresql.Driver

fun Application.configureDatabases() {

    val host: String = environment.config.propertyOrNull("database.host")?.getString()
        ?: throw UnableToAccessDatabaseException("No host defined")
    val name: String = environment.config.propertyOrNull("database.name")?.getString()
        ?: throw UnableToAccessDatabaseException("No database name defined")
    val username: String = environment.config.propertyOrNull("database.username")?.getString()
        ?: throw UnableToAccessDatabaseException("No database username defined")
    val password: String = environment.config.propertyOrNull("database.password")?.getString()
        ?: throw UnableToAccessDatabaseException("No database password defined")

    val database = Database.connect(
        url = "jdbc:postgresql://$host/$name",
        user = username,
        driver = Driver::class.java.canonicalName,
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
