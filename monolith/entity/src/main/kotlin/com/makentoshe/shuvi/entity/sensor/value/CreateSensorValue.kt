package com.makentoshe.shuvi.entity.sensor.value

import com.makentoshe.shuvi.entity.SensorId

data class CreateSensorValue(
    val valueId: SensorValueId?,
    val sensorId: SensorId,
    val value: Int,
)

