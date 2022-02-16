package com.makentoshe.shuvi.response.service

import com.makentoshe.shuvi.entity.CreatedDevice
import com.makentoshe.shuvi.entity.service.NetworkDevice
import kotlinx.serialization.Serializable

/**
 * Response on the create device request
 */
@Serializable
sealed class NetworkCreatedDeviceResponse {

    /** Device that should be created */
    abstract val device: NetworkDevice

    @Serializable
    data class Success(
        override val device: NetworkDevice,
    ) : NetworkCreatedDeviceResponse() {
        constructor(createdDevice: CreatedDevice) : this(NetworkDevice(createdDevice.device))
    }

    @Serializable
    data class Failure(
        override val device: NetworkDevice,
        val exception: String,
        val message: String,
    ) : NetworkCreatedDeviceResponse() {
        constructor(device: NetworkDevice, exception: Exception) : this(
            device = device,
            exception = exception.javaClass.simpleName,
            message = exception.message ?: "",
        )
    }
}