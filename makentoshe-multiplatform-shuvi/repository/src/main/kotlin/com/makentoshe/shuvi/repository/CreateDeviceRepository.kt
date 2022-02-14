package com.makentoshe.shuvi.repository

import com.makentoshe.shuvi.entity.Device

interface CreateDeviceRepository {

    fun createDevice(device: Device)
}