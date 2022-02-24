package com.makentoshe.shuvi.repository.device

import com.makentoshe.shuvi.database.Database
import com.makentoshe.shuvi.entity.DeviceId
import com.makentoshe.shuvi.repository.GetDeviceRepository
import com.makentoshe.shuvi.response.repository.GetDeviceResponse

class GetDeviceRepositoryImpl(private val database: Database) : GetDeviceRepository {
    override fun getDevice(id: DeviceId): GetDeviceResponse {
        return database.device().get().id(id).mapLeft { databaseDevice -> databaseDevice.toDevice() }
    }
}