package com.makentoshe.shuvi.entity.sensor.value

import com.makentoshe.shuvi.entity.SensorId

data class CreateSensorValue(
    val id: String?,
    val sensorId: SensorId,
    val value: Int,
)

