package database

import kotlinx.coroutines.reactive.asFlow

abstract class IdTable<T>(private val tableName: String) {
    abstract val id: Column<T>
    private val columns = mutableListOf<Column<*>>()

    fun string(name: String) = Column(name = name, columnType = String::class.java).also { columns.add(it) }
    fun int(name: String) = Column(name = name, columnType = Int::class.java).also { columns.add(it) }

    context(ConnectionContext)
    fun selectAll(): ResultSet =
        ResultSet(
            connection.createStatement("SELECT ${joinColumnNames()} FROM $tableName").execute()
            .asFlow()
        )

    private fun joinColumnNames(): String =
        columns.joinToString(separator = ",") {
            it.name
        }
}

data class Column<T>(
    val name: String,
    val columnType: Class<T>,
)