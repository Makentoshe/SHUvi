package com.makentoshe.shuvi.repository

import com.makentoshe.shuvi.entity.CreateDevice
import com.makentoshe.shuvi.response.repository.CreatedDeviceResponse
import com.makentoshe.shuvi.entity.Device

interface CreateDeviceRepository {

    fun createDevice(device: CreateDevice): CreatedDeviceResponse
}