import connection.CustomFactory
import io.r2dbc.spi.Connection
import io.r2dbc.spi.ConnectionFactory
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.reactive.asFlow
import kotlinx.coroutines.reactive.awaitFirst
import kotlinx.coroutines.reactive.collect
import kotlin.system.measureTimeMillis

/**
 * An R2DBC client that uses Kotlin coroutines.
 */
class R2DBCIO(private val connectionFactory: ConnectionFactory) {
    /**
     * Opens a connection from the factory.
     */
    private suspend fun factoryOpen(): Connection {
        return this.connectionFactory.create().awaitFirst()
    }

    /**
     * Open a connection and pass on a handler.
     */
    suspend fun open(invoke: suspend (Handle) -> Unit) {
        val connection = factoryOpen()
        val handle = Handle(connection)
        invoke(handle)
    }
}

/**
 * Wrapper for [Connection].
 */
class Handle(val connection: Connection) {
    fun selectAll(sql: String): Flow<Unit> {
        val listOfItems = mutableListOf<String?>()

        return connection.createStatement(sql).fetchSize(10000).execute()
            .asFlow()
            .map { result ->
                result.map { row ->
                    row.get(0)
                }.collect {
                    listOfItems.add(it.toString())
                }
            }
    }
}

suspend fun main() {
    val time = measureTimeMillis {
        R2DBCIO(CustomFactory.getFactory()).open {
            it.selectAll("SELECT BS_ID FROM BS_BST").collect()
        }
    }
    println(time / 1000)
}