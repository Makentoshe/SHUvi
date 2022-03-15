package com.makentoshe.shuvi.response.service.sensor

import com.makentoshe.shuvi.entity.sensor.value.CreatedSensorValue
import kotlinx.serialization.Serializable

/**
 * Response on the create device request
 */
@Serializable
sealed class NetworkValueSensorResponse {

    @Serializable
    data class Success(
        val sensorId: String,
        val value: Int,
        val timestamp: String,
    ) : NetworkValueSensorResponse() {
        constructor(createdSensorValue: CreatedSensorValue) : this(
            sensorId = createdSensorValue.sensorId.string,
            value = createdSensorValue.value,
            timestamp = createdSensorValue.timestamp,
        )
    }

    @Serializable
    data class Failure(
        val exception: String,
        val message: String,
    ) : NetworkValueSensorResponse() {
        constructor(exception: Exception) : this(
            exception = exception.javaClass.simpleName,
            message = exception.message ?: "",
        )
    }
}
