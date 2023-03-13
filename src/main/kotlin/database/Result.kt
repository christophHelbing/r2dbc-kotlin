package database

import io.r2dbc.spi.Readable
import io.r2dbc.spi.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.reactive.collect


class ResultSet(private val resultFlow: Flow<Result>) {
    suspend fun <T> map(mapper: (ResultRow) -> T): List<T> {
        val listOfItems = mutableListOf<T>()
        resultFlow.map { result ->
            result.map { row ->
                mapper(ResultRow(row))
            }.collect {
                listOfItems.add(it)
            }
        }.collect()

        return listOfItems
    }
}

class ResultRow(private val row: Readable) {
    operator fun <T> get(column: Column<T>): T = row.get(column.name, column.columnType)!!
}