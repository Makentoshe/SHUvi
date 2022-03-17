package com.makentoshe.shuvi.entity.sensor.value

import com.makentoshe.shuvi.entity.SensorId

data class Value(
    val valueId: SensorValueId,
    val sensorId: SensorId,
    val value: Int,
    val timestamp: String,
)