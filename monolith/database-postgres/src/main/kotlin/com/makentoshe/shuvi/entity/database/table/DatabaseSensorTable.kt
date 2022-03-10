package com.makentoshe.shuvi.entity.database.table

import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table

object DatabaseSensorTable : Table() {
    val id: Column<String> = varchar("id", 16).uniqueIndex()
    val title: Column<String> = varchar("title", 32)

    override val primaryKey = PrimaryKey(id)
}
