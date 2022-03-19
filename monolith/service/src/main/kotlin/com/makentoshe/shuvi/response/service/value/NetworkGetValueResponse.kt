package com.makentoshe.shuvi.response.service.value

import com.makentoshe.shuvi.entity.sensor.value.CreatedSensorValue
import com.makentoshe.shuvi.entity.service.value.NetworkValue
import kotlinx.serialization.Serializable

/**
 * Response on the create device request
 */
@Serializable
sealed class NetworkGetValueResponse {

    @Serializable
    data class Success(
        val values: List<NetworkValue>,
    ) : NetworkGetValueResponse()

    @Serializable
    data class Failure(
        val exception: String,
        val message: String,
    ) : NetworkGetValueResponse() {
        constructor(exception: Exception) : this(
            exception = exception.javaClass.simpleName,
            message = exception.message ?: "",
        )
    }
}
