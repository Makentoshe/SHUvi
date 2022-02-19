package com.makentoshe.shuvi.response.service

import com.makentoshe.shuvi.entity.CreatedDevice
import com.makentoshe.shuvi.entity.service.NetworkDevice
import kotlinx.serialization.Serializable

/**
 * Response on the create device request
 */
@Serializable
sealed class NetworkCreatedDeviceResponse {

    @Serializable
    data class Success(
        val device: NetworkDevice,
    ) : NetworkCreatedDeviceResponse() {
        constructor(createdDevice: CreatedDevice) : this(NetworkDevice(createdDevice.device))
    }

    @Serializable
    data class Failure(
        val exception: String,
        val message: String,
        val device: NetworkDevice? = null,
    ) : NetworkCreatedDeviceResponse() {
        constructor(device: NetworkDevice, exception: Exception) : this(
            device = device,
            exception = exception.javaClass.simpleName,
            message = exception.message ?: "",
        )

        constructor(exception: Exception) : this(
            exception = exception.javaClass.simpleName,
            message = exception.message ?: "",
        )
    }
}