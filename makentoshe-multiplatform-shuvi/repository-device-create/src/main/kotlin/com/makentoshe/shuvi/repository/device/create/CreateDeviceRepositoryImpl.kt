package com.makentoshe.shuvi.repository.device.create

import com.makentoshe.shuvi.database.Database
import com.makentoshe.shuvi.entity.CreatedDevice
import com.makentoshe.shuvi.entity.CreatedDeviceResponse
import com.makentoshe.shuvi.entity.Device
import com.makentoshe.shuvi.entity.database.DatabaseDevice
import com.makentoshe.shuvi.repository.CreateDeviceRepository

class CreateDeviceRepositoryImpl(private val database: Database) : CreateDeviceRepository {
    override fun createDevice(device: Device): CreatedDeviceResponse {
        return database.device().insert().insertDevice(DatabaseDevice(device)).mapLeft { insertedDatabaseDevice ->
            CreatedDevice(device, insertedDatabaseDevice.wasAcknowledged)
        }
    }
}