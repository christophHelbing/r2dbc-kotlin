package connection

import io.r2dbc.spi.ConnectionFactories
import io.r2dbc.spi.ConnectionFactory
import io.r2dbc.spi.ConnectionFactoryOptions

object CustomFactory {
    fun getFactory(): ConnectionFactory {
        val driver = System.getenv("DRIVER")
        val host = System.getenv("HOST")
        val port = System.getenv("PORT").toInt()
        val serviceName = System.getenv("SERVICE_NAME")
        val user = System.getenv("USER")
        val password = System.getenv("PASSWORD")


        return ConnectionFactories.get(
            ConnectionFactoryOptions.builder()
                .option(ConnectionFactoryOptions.DRIVER, driver)
                .option(ConnectionFactoryOptions.HOST, host)
                .option(ConnectionFactoryOptions.PORT, port)
                .option(ConnectionFactoryOptions.DATABASE, serviceName)
                .option(ConnectionFactoryOptions.USER, user)
                .option(ConnectionFactoryOptions.PASSWORD, password)
                .build())
    }
}