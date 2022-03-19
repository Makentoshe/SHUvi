package com.makentoshe.shuvi.entity.service.value

import com.makentoshe.shuvi.entity.sensor.value.Value

@kotlinx.serialization.Serializable
data class NetworkValue(
    val valueId: String,
    val sensorId: String,
    val value: Int,
    val timestamp: String,
) {
    constructor(value: Value) : this(
        valueId = value.valueId.string,
        sensorId = value.sensorId.string,
        value = value.value,
        timestamp = value.timestamp,
    )
}