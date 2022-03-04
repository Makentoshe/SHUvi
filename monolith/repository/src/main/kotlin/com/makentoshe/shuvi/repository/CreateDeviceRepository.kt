package com.makentoshe.shuvi.repository

import com.makentoshe.shuvi.entity.device.CreateDevice
import com.makentoshe.shuvi.response.repository.CreatedDeviceResponse

interface CreateDeviceRepository {

    fun createDevice(device: CreateDevice): CreatedDeviceResponse
}