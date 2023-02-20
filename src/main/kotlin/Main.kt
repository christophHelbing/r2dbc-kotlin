import connection.ConnectionFactory
import io.r2dbc.spi.Connection
import reactor.core.publisher.Flux


fun main() {
    Flux.usingWhen(
        ConnectionFactory.getFactory().create(),
        { connection ->
            Flux.from(
                connection.createStatement("SELECT 'Hello, Oracle' FROM sys.dual")
                    .execute()
            )
                .flatMap { result ->
                    result.map { row ->
                        row.get(
                            0,
                            String::class.java
                        )
                    }
                }
        },
        Connection::close
    )
        .doOnNext(System.out::println)
        .doOnError(Throwable::printStackTrace)
        .subscribe()
}