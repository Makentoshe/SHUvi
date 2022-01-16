package com.makentoshe.shuvi.database.device

import com.makentoshe.shuvi.entity.Device

interface GetAllDeviceDatabase {
    fun getDevices(): List<Device>
}