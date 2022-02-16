package com.makentoshe.shuvi.repository

import com.makentoshe.shuvi.entity.DeviceId
import com.makentoshe.shuvi.response.repository.GetDeviceResponse

interface DeviceRepository {
    fun getDevice(id: DeviceId): GetDeviceResponse
}