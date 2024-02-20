package fr.rage.lafie.data.database.dao

import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import java.util.*

abstract class BaseDao<E : UUIDEntity>(
    protected val database: Database,
    private val entityClass: UUIDEntityClass<E>
) {

    abstract suspend fun create(item: E): E

    suspend fun getById(id: UUID): E? = runDbQuery {
        entityClass.findById(id)
    }

    suspend fun delete(id: UUID) = getById(id).let { item ->
        runDbQuery { item?.delete() }
    }

    protected suspend inline fun <T> runDbQuery(crossinline action: () -> T): T =
        newSuspendedTransaction(Dispatchers.IO, database) {
            action()
        }
}