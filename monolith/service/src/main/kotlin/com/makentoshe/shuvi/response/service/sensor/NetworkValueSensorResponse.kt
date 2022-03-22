package com.makentoshe.shuvi.response.service.sensor

import com.makentoshe.shuvi.entity.sensor.value.CreatedValue
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
        constructor(createdValue: CreatedValue) : this(
            sensorId = createdValue.value.sensorId.string,
            value = createdValue.value.value,
            timestamp = createdValue.value.timestamp,
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
