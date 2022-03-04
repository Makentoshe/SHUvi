package com.makentoshe.shuvi.entity.service

import com.makentoshe.shuvi.entity.device.Device
import kotlinx.serialization.Serializable

/** Requireable for devices getting */
@Serializable
data class NetworkDevice2(val id: String, val title: String) {
    constructor(device: Device) : this(device.id.string, device.title)
}