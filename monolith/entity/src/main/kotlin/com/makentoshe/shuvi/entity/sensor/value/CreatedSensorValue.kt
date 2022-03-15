package com.makentoshe.shuvi.entity.sensor.value

import com.makentoshe.shuvi.entity.SensorId

data class CreatedSensorValue(
    val id: String,
    val sensorId: SensorId,
    val value: Int,
    val timestamp: String,
)