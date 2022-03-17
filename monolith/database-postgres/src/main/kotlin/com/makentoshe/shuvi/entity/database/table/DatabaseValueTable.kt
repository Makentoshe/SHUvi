package com.makentoshe.shuvi.entity.database.table

import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table

object DatabaseValueTable : Table() {
    val valueId: Column<String> = varchar("valueId", 16).uniqueIndex()
    val sensorId: Column<String> = varchar("sensorId", 16)
    val value: Column<Int> = integer("value")
    val timestamp: Column<String> = varchar("timestamp", 32)

    override val primaryKey = PrimaryKey(valueId)
}