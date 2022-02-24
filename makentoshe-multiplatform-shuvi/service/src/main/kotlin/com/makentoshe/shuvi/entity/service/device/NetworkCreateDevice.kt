package com.makentoshe.shuvi.entity.service.device

import com.makentoshe.shuvi.entity.device.CreateDevice
import com.makentoshe.shuvi.entity.DeviceId
import com.makentoshe.shuvi.entity.service.NetworkSensor2
import com.makentoshe.shuvi.entity.service.sensor.NetworkCreateSensor
import kotlinx.serialization.Serializable

/**
 * Requireable for devices creation and getting
 *
 * See CreateDeviceServiceImpl, DeviceServiceImpl, DevicesServiceImpl
 * */
@Serializable
data class NetworkCreateDevice(val id: String? = null, val title: String? = id, val sensors: List<NetworkCreateSensor>)

fun NetworkCreateDevice.toCreateDevice() = CreateDevice(id?.let(::DeviceId), title ?: "")