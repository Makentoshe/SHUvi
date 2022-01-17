package com.makentoshe.shuvi.entity.service

import com.makentoshe.shuvi.entity.Device
import com.makentoshe.shuvi.entity.DeviceId
import kotlinx.serialization.Serializable

@Serializable
data class NetworkDevice(val id: String) {
    constructor(device: Device) : this(device.id.string)

    fun toDevice() = Device(DeviceId(id))
}
