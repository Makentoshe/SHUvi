package com.makentoshe.shuvi.repository.device

import com.makentoshe.shuvi.database.Database
import com.makentoshe.shuvi.entity.Device
import com.makentoshe.shuvi.repository.DeviceRepository

class DeviceRepositoryImpl(private val database: Database) : DeviceRepository {
    override fun getDevices(): List<Device> {
        return database.device().getAll().getDevices().map { it.toDevice() }
    }
}