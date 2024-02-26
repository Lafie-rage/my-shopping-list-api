package fr.rage.lafie.plugins.di

import fr.rage.lafie.data.database.dao.ShoppingItemDao
import fr.rage.lafie.data.database.dao.ShoppingListDao
import fr.rage.lafie.repository.ShoppingItemRepository
import fr.rage.lafie.repository.ShoppingListRepository
import fr.rage.lafie.service.ShoppingItemService
import fr.rage.lafie.service.ShoppingListService
import io.ktor.server.application.*
import org.jetbrains.exposed.sql.transactions.TransactionManager
import org.koin.dsl.module
import org.koin.ktor.plugin.Koin
import org.koin.logger.slf4jLogger

fun Application.configureKoin() {
    install(Koin) {
        slf4jLogger()
        modules(
            daoModule,
            repositoryModule,
            serviceModule,
        )
    }
}


val daoModule = module {
    val database = TransactionManager.defaultDatabase!!
    single { ShoppingItemDao(database) }
    single { ShoppingListDao(database) }
}

val repositoryModule = module {
    single {
        ShoppingItemRepository(get())
    }
    single {
        ShoppingListRepository(get())
    }
}

val serviceModule = module {
    single {
        ShoppingItemService(get(), get())
    }
    single {
        ShoppingListService(get(), get())
    }
}