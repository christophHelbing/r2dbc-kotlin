package example

import database.Column
import database.IdTable

data class ExampleEntity (
    val id: Int,
    val columnString: String,
    val columnInt: Int,
)

object ExampleTable : IdTable<Int>(tableName = "TABLE") {
    override val id: Column<Int> = int("ID")
    val columnString = string(name = "COLUMN_STRING")
    val columnInt = int(name = "COLUMN_INT")
}