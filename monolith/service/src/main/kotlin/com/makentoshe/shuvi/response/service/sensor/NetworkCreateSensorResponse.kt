package com.makentoshe.shuvi.response.service.sensor

import com.makentoshe.shuvi.entity.service.NetworkSensor
import kotlinx.serialization.Serializable

/**
 * Response on the create device request
 */
@Serializable
sealed class NetworkCreateSensorResponse {

    @Serializable
    data class Success(
        val sensor: NetworkSensor,
    ) : NetworkGetSensorResponse()

    @Serializable
    data class Failure(
        val exception: String,
        val message: String,
    ) : NetworkGetSensorResponse() {
        constructor(exception: Exception) : this(
            exception = exception.javaClass.simpleName,
            message = exception.message ?: "",
        )
    }
}
