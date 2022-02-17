package com.makentoshe.shuvi.response.service

import com.makentoshe.shuvi.entity.DeletedDevice
import com.makentoshe.shuvi.entity.service.NetworkDevice
import com.makentoshe.shuvi.entity.service.NetworkDeviceId
import kotlinx.serialization.Serializable

@Serializable
sealed class NetworkDeleteDeviceResponse {

    @Serializable
    data class Success(val device: NetworkDevice) : NetworkDeleteDeviceResponse() {

        constructor(deletedDevice: DeletedDevice) : this(NetworkDevice(deletedDevice.device))
    }

    @Serializable
    data class Failure(
        val device: NetworkDeviceId,
        val exception: String,
        val message: String,
    ) : NetworkDeleteDeviceResponse() {
        constructor(deviceId: NetworkDeviceId, exception: Exception) : this(
            device = deviceId,
            exception = exception.javaClass.simpleName,
            message = exception.message ?: "",
        )
    }
}