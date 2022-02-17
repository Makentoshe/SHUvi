package com.makentoshe.shuvi.repository.device.delete

import com.makentoshe.shuvi.common.Either
import com.makentoshe.shuvi.common.rightOrNull
import com.makentoshe.shuvi.database.Database
import com.makentoshe.shuvi.database.device.DeviceDatabase
import com.makentoshe.shuvi.entity.DeviceId
import com.makentoshe.shuvi.entity.database.DatabaseDeviceId
import com.makentoshe.shuvi.repository.DeleteDeviceRepository
import com.makentoshe.shuvi.response.repository.DeletedDeviceResponse

class DeleteDeviceRepositoryImpl(private val database: Database) : DeleteDeviceRepository {
    override fun deleteDevice(deviceId: DeviceId): DeletedDeviceResponse {
        return database.device().delete().id(DatabaseDeviceId(deviceId.string)).mapLeft { databaseDeletedDevice ->
            databaseDeletedDevice.toDeletedDevice()
        }
    }
}