package example

import database.ConnectionContext
import database.transaction
import kotlinx.coroutines.runBlocking

fun main() {
    transaction { connection ->
        runBlocking {
            with(ConnectionContext(connection = connection)) {
                ExampleTable.selectAll()
                    .map {
                        ExampleEntity(
                            id = it[ExampleTable.id],
                            columnString = it[ExampleTable.columnString],
                            columnInt = it[ExampleTable.columnInt],
                        )
                    }
            }
        }
    }
}