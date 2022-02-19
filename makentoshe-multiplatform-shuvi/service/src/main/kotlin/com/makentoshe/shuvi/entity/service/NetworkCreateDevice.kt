package com.makentoshe.shuvi.entity.service

import com.makentoshe.shuvi.entity.CreateDevice
import com.makentoshe.shuvi.entity.Device
import com.makentoshe.shuvi.entity.DeviceId
import kotlinx.serialization.Serializable

/**
 * Requireable for devices creation and getting
 *
 * See CreateDeviceServiceImpl, DeviceServiceImpl, DevicesServiceImpl
 * */
@Serializable
data class NetworkCreateDevice(val id: String? = null, val title: String, val sensors: List<NetworkSensor>) {
    constructor(device: Device) : this(device.id.string, device.title, device.sensors.map(::NetworkSensor))

    fun toCreateDevice() = CreateDevice(id?.let(::DeviceId), title, sensors.map { it.toSensor() })
}