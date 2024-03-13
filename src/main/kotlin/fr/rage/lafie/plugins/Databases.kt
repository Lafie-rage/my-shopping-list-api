package fr.rage.lafie.plugins

import fr.rage.lafie.data.database.table.ShoppingItemTable
import fr.rage.lafie.data.database.table.ShoppingListTable
import io.ktor.server.application.*
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

fun Application.configureDatabases() {
    val database = Database.connect(
        url = "jdbc:h2:mem:my-shopping-list-db;DB_CLOSE_DELAY=-1",
        user = "root",
        driver = "org.h2.Driver",
        password = "",
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
