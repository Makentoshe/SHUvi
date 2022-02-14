package com.makentoshe.shuvi.entity.service

import com.makentoshe.shuvi.entity.CreatedDevice
import kotlinx.serialization.Serializable
import java.lang.Exception

@Serializable
sealed class NetworkCreatedDevice {
    abstract val device: NetworkDevice

    @Serializable
    data class Success(
        override val device: NetworkDevice,
    ) : NetworkCreatedDevice() {
        constructor(createdDevice: CreatedDevice) : this(NetworkDevice(createdDevice.device))
    }

    @Serializable
    data class Failure(
        override val device: NetworkDevice,
        val exception: String,
        val message: String,
    ) : NetworkCreatedDevice() {
        constructor(device: NetworkDevice, exception: Exception) : this(
            device = device,
            exception = exception.javaClass.simpleName,
            message = exception.message ?: "",
        )
    }
}