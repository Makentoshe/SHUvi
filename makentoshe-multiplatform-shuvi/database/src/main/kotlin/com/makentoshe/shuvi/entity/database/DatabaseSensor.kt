package com.makentoshe.shuvi.entity.database

import com.makentoshe.shuvi.entity.Sensor
import com.makentoshe.shuvi.entity.SensorId

data class DatabaseSensor(val id: String) {
    @Suppress("unused", "PropertyName")
    val _id: String = id

    constructor(sensor: Sensor) : this(sensor.id.string)

    fun toSensor() = Sensor(SensorId(id))
}
