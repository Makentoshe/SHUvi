package com.makentoshe.shuvi.entity.service

import com.makentoshe.shuvi.entity.Device
import com.makentoshe.shuvi.entity.DeviceId
import kotlinx.serialization.Serializable

/**
 * Requireable for devices creation and getting
 *
 * See CreateDeviceServiceImpl, DeviceServiceImpl, DevicesServiceImpl
 * */
@Serializable
data class NetworkDevice(val id: String, val title: String, val sensors: List<NetworkSensor>) {
    constructor(device: Device) : this(device.id.string, device.title, device.sensors.map(::NetworkSensor))

    fun toDevice() = Device(DeviceId(id), title, sensors.map { it.toSensor() })
}
