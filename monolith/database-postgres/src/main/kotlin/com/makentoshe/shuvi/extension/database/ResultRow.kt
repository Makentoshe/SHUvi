package com.makentoshe.shuvi.extension.database

import com.makentoshe.shuvi.entity.database.DatabaseDevice
import com.makentoshe.shuvi.entity.database.DatabaseSensor
import com.makentoshe.shuvi.entity.database.crossref.DatabaseDeviceSensorsCrossref
import com.makentoshe.shuvi.entity.database.table.DatabaseDeviceSensorsCrossrefTable
import com.makentoshe.shuvi.entity.database.table.DatabaseDeviceTable
import com.makentoshe.shuvi.entity.database.table.DatabaseSensorTable
import com.makentoshe.shuvi.entity.database.table.DatabaseValueTable
import com.makentoshe.shuvi.entity.database.value.DatabaseValue
import org.jetbrains.exposed.sql.ResultRow

internal fun ResultRow.toDatabaseDevice() = DatabaseDevice(
    id = this[DatabaseDeviceTable.id],
    title = this[DatabaseDeviceTable.title]
)

internal fun ResultRow.toDatabaseSensor() = DatabaseSensor(
    id = this[DatabaseSensorTable.id],
    title = this[DatabaseSensorTable.title],
)

internal fun ResultRow.toDatabaseDeviceSensorCrossref() = DatabaseDeviceSensorsCrossref(
    deviceId = this[DatabaseDeviceSensorsCrossrefTable.deviceId],
    sensorId = this[DatabaseDeviceSensorsCrossrefTable.sensorId],
)

internal fun ResultRow.toDatabaseValue() = DatabaseValue(
    valueId = this[DatabaseValueTable.valueId],
    sensorId = this[DatabaseValueTable.sensorId],
    value = this[DatabaseValueTable.value],
    timestamp = this[DatabaseValueTable.timestamp],
)
