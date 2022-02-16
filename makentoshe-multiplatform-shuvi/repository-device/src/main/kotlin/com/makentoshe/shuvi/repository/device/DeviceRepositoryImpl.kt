package com.makentoshe.shuvi.repository.device

import com.makentoshe.shuvi.database.Database
import com.makentoshe.shuvi.repository.DeviceRepository
import com.makentoshe.shuvi.response.repository.GetDevicesResponse

class DeviceRepositoryImpl(private val database: Database) : DeviceRepository {
    override fun getDevices(): GetDevicesResponse {
        return database.device().get().getDevices().mapLeft { databaseDevices ->
            databaseDevices.map { databaseDevice -> databaseDevice.toDevice() }
        }
    }
}
