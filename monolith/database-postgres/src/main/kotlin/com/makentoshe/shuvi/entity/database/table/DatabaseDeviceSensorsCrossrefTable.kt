package com.makentoshe.shuvi.entity.database.table

import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table

object DatabaseDeviceSensorsCrossrefTable : Table() {
    val deviceId: Column<String> = varchar("deviceId", 16)
    val sensorId: Column<String> = varchar("sensorId", 16).uniqueIndex()

    override val primaryKey = PrimaryKey(sensorId)
}