package com.makentoshe.shuvi.repository.devices

import com.makentoshe.shuvi.database.Database
import com.makentoshe.shuvi.repository.DevicesRepository
import com.makentoshe.shuvi.response.repository.GetDevicesResponse

class DevicesRepositoryImpl(private val database: Database) : DevicesRepository {
    override fun getDevices(): GetDevicesResponse {
        return database.device().get().all().mapLeft { databaseDevices ->
            databaseDevices.map { databaseDevice -> databaseDevice.toDevice() }
        }
    }
}
