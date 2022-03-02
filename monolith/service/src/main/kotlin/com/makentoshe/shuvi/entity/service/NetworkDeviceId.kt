package com.makentoshe.shuvi.entity.service

import com.makentoshe.shuvi.entity.DeviceId
import kotlinx.serialization.Serializable

/**
 * Requireable for device deletion
 *
 * See DeleteDeviceServiceImpl for use case.
 * */
@Serializable
data class NetworkDeviceId(val id: String) {
    fun toDeviceId() = DeviceId(id)
}