package com.makentoshe.shuvi.entity.service

import com.makentoshe.shuvi.entity.Device
import com.makentoshe.shuvi.entity.DeviceId
import kotlinx.serialization.Serializable

@Serializable
data class NetworkDevice(val id: String, val title: String) {
    constructor(device: Device) : this(device.id.string, device.title)

    fun toDevice() = Device(DeviceId(id), title)
}
