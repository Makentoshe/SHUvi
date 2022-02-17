package com.makentoshe.shuvi.repository.device.delete

import com.makentoshe.shuvi.common.Either
import com.makentoshe.shuvi.entity.DeletedDevice
import com.makentoshe.shuvi.entity.Device
import com.makentoshe.shuvi.entity.DeviceId
import com.makentoshe.shuvi.repository.DeleteDeviceRepository
import com.makentoshe.shuvi.response.repository.DeletedDeviceResponse

class DeleteDeviceRepositoryImpl : DeleteDeviceRepository {
    override fun deleteDevice(deviceId: DeviceId): DeletedDeviceResponse {
        return Either.Left(DeletedDevice(Device(deviceId, "Repositoried"), true))
    }
}