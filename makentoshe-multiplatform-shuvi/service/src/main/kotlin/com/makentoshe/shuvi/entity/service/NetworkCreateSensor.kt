package com.makentoshe.shuvi.entity.service

import com.makentoshe.shuvi.entity.CreateSensor
import com.makentoshe.shuvi.entity.Sensor
import com.makentoshe.shuvi.entity.SensorId
import kotlinx.serialization.Serializable

/**
 * Requireable for devices creation and for sensors creation
 *
 * See CreateDeviceServiceImpl
 * */
@Serializable
data class NetworkCreateSensor(val id: String? = null) {
    constructor(sensor: Sensor) : this(sensor.id.string)

    fun toCreateSensor() = CreateSensor(id?.let(::SensorId))
}