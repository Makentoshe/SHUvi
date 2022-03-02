package com.makentoshe.shuvi.entity.database

import com.makentoshe.shuvi.entity.device.Device
import com.makentoshe.shuvi.entity.DeviceId
import com.makentoshe.shuvi.entity.device.CreateDevice

data class DatabaseDevice(val id: String, val title: String) {
    @Suppress("unused", "PropertyName")
    val _id: String = id

    constructor(deviceId: DeviceId, createDevice: CreateDevice) : this(deviceId.string, createDevice.title)

    constructor(device: Device): this(device.id.string, device.title)

    fun toDevice() = Device(DeviceId(id), title)

}
