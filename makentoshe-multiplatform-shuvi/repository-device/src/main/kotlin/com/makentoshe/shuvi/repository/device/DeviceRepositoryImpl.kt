package com.makentoshe.shuvi.repository.device

import com.makentoshe.shuvi.repository.DeviceRepository

class DeviceRepositoryImpl : DeviceRepository {
    override fun getDevices(): List<String> {
        return listOf("Device1", "Device2", "Device3")
    }
}