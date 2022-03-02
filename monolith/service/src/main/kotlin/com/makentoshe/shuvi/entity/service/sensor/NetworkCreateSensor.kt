package com.makentoshe.shuvi.entity.service.sensor

import com.makentoshe.shuvi.entity.CreateSensor
import com.makentoshe.shuvi.entity.SensorId


/** Requireable for sensor instance creation */
@kotlinx.serialization.Serializable
data class NetworkCreateSensor(val id: String? = null, val title: String? = id)

fun NetworkCreateSensor.toCreateSensor() = CreateSensor(id?.let(::SensorId), title ?: "")
