package com.makentoshe.shuvi.response.service.sensor

import com.makentoshe.shuvi.entity.CreateSensor
import com.makentoshe.shuvi.entity.SensorId
import kotlinx.serialization.Serializable

/** Requireable for sensor creation */
@Serializable
data class NetworkCreateSensor(val id: String? = null, val title: String? = id) {
    fun toCreateSensor() = CreateSensor(id = id?.let(::SensorId), title = title ?: "")
}

