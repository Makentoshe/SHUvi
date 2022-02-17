package com.makentoshe.shuvi.entity.service

import com.makentoshe.shuvi.entity.Sensor
import com.makentoshe.shuvi.entity.SensorId
import kotlinx.serialization.Serializable

/**
 * Requireable for devices creation and getting, and for sensors creation
 *
 * See CreateDeviceServiceImpl, DeviceServiceImpl, DevicesServiceImpl
 * */
@Serializable
data class NetworkSensor(val id: String) {
    constructor(sensor: Sensor) : this(sensor.id.string)

    fun toSensor() = Sensor(SensorId(id))
}
