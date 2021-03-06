package com.makentoshe.shuvi.response.service

import com.makentoshe.shuvi.entity.service.NetworkDevice2
import kotlinx.serialization.Serializable

/**
 * Response on the get devices request
 */
@Serializable
sealed class NetworkGetDevicesResponse {

    @Serializable
    data class Success(
        val devices: List<NetworkDevice2>,
    ) : NetworkGetDevicesResponse()

    @Serializable
    data class Failure(
        val exception: String,
        val message: String,
    ) : NetworkGetDevicesResponse() {
        constructor(exception: Exception) : this(
            exception = exception.javaClass.simpleName,
            message = exception.message ?: "",
        )
    }
}