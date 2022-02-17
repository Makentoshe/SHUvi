package com.makentoshe.shuvi.entity.database

import com.makentoshe.shuvi.entity.Device
import com.makentoshe.shuvi.entity.DeviceId
import com.makentoshe.shuvi.entity.Sensor

data class DatabaseDevice(val id: String, val title: String) {
    @Suppress("unused", "PropertyName")
    val _id: String = id

    constructor(device: Device) : this(device.id.string, device.title)

    fun toDevice(sensors: List<Sensor>) = Device(DeviceId(id), title, sensors)

}
