package com.makentoshe.shuvi.entity.service

import com.makentoshe.shuvi.entity.CreateSensor
import com.makentoshe.shuvi.entity.Sensor
import com.makentoshe.shuvi.entity.SensorId
import kotlinx.serialization.Serializable

/**
 * Requireable for devices creation and getting, and for sensors creation
 *
 * See CreateDeviceServiceImpl, DeviceServiceImpl, DevicesServiceImpl
 * */
@Serializable
@Deprecated("")
data class NetworkSensor(val id: String, val title: String) {
    constructor(sensor: Sensor) : this(sensor.id.string, sensor.title)

    fun toSensor() = Sensor(SensorId(id), title)
}

/**
 * Requireable for devices creation and getting, and for sensors creation
 *
 * See CreateDeviceServiceImpl, DeviceServiceImpl, DevicesServiceImpl
 * */
@Serializable
data class NetworkSensor2(val id: String? = null, val title: String? = id) {
    constructor(sensor: Sensor) : this(sensor.id.string, sensor.title)

    fun toCreateSensor() = CreateSensor(id?.let(::SensorId), title ?: "")
}
