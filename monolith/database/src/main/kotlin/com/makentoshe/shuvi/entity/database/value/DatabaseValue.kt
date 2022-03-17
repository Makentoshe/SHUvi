package com.makentoshe.shuvi.entity.database.value

import com.makentoshe.shuvi.entity.SensorId
import com.makentoshe.shuvi.entity.sensor.value.CreateSensorValue
import com.makentoshe.shuvi.entity.sensor.value.SensorValueId
import com.makentoshe.shuvi.entity.sensor.value.Value

data class DatabaseValue(
    val valueId: String,
    val sensorId: String, //TODO might be crossreffed
    val value: Int,
    val timestamp: String,
) {
    constructor(valueId: SensorValueId, timestamp: String, createSensorValue: CreateSensorValue) : this(
        valueId = createSensorValue.valueId?.string ?: valueId.string,
        sensorId = createSensorValue.sensorId.string,
        value = createSensorValue.value,
        timestamp = timestamp
    )
}

fun DatabaseValue.toValue() = Value(SensorValueId(valueId), SensorId(sensorId), value, timestamp)
