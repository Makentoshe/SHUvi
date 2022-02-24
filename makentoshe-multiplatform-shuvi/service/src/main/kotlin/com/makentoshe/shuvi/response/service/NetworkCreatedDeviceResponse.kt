package com.makentoshe.shuvi.response.service

import com.makentoshe.shuvi.entity.CreatedSensor
import com.makentoshe.shuvi.entity.device.CreatedDevice
import com.makentoshe.shuvi.entity.service.NetworkDevice2
import com.makentoshe.shuvi.entity.service.NetworkSensor2
import kotlinx.serialization.Serializable

/**
 * Response on the create device request
 */
@Serializable
sealed class NetworkCreatedDeviceResponse {

    @Serializable
    data class Success(
        val createdDevice: NetworkDevice2,
        val createdSensors: List<NetworkSensor2>
    ) : NetworkCreatedDeviceResponse() {
        constructor(
            createdDevice: CreatedDevice,
            createdSensors: List<CreatedSensor>,
        ) : this(
            NetworkDevice2(createdDevice.device),
            createdSensors.map { NetworkSensor2(it.sensor) },
        )
    }

    @Serializable
    data class Failure(
        val exception: String,
        val message: String,
        val device: NetworkDevice2? = null,
    ) : NetworkCreatedDeviceResponse() {
        constructor(device: NetworkDevice2, exception: Exception) : this(
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