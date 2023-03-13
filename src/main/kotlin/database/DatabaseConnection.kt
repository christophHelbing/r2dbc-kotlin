package database

import configuration.Configuration
import io.r2dbc.spi.Connection
import io.r2dbc.spi.ConnectionFactories
import io.r2dbc.spi.ConnectionFactoryOptions
import kotlinx.coroutines.reactive.awaitFirst
import kotlinx.coroutines.runBlocking

object DatabaseConnection {
    private val connectionFactory = ConnectionFactories.get(
        ConnectionFactoryOptions.builder()
            .option(ConnectionFactoryOptions.DRIVER, Configuration.driver)
            .option(ConnectionFactoryOptions.HOST, Configuration.host)
            .option(ConnectionFactoryOptions.PORT, Configuration.port)
            .option(ConnectionFactoryOptions.DATABASE, Configuration.serviceName)
            .option(ConnectionFactoryOptions.USER, Configuration.userLegacy)
            .option(ConnectionFactoryOptions.PASSWORD, Configuration.passwordlegacy)
            .build()
    )

    fun connectToLegacyDatabase(): Connection = runBlocking { connectionFactory.create().awaitFirst() }
}

data class ConnectionContext(
    val connection: Connection
)

fun<T> transaction(databaseCall: (Connection) -> List<T>): List<T> {
    val connection = DatabaseConnection.connectToLegacyDatabase()
    return databaseCall(connection)
}
