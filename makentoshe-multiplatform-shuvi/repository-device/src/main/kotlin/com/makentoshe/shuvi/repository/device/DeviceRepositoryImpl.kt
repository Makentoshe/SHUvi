package com.makentoshe.shuvi.repository.device

import com.makentoshe.shuvi.common.Either
import com.makentoshe.shuvi.entity.Device
import com.makentoshe.shuvi.entity.DeviceId
import com.makentoshe.shuvi.repository.DeviceRepository
import com.makentoshe.shuvi.response.repository.GetDeviceResponse

class DeviceRepositoryImpl : DeviceRepository {
    override fun getDevice(id: DeviceId): GetDeviceResponse {
        return Either.Left(Device(id, "Repositoried"))
    }
}