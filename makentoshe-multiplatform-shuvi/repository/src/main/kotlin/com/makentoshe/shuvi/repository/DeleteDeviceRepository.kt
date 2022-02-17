package com.makentoshe.shuvi.repository

import com.makentoshe.shuvi.entity.DeviceId
import com.makentoshe.shuvi.response.repository.DeletedDeviceResponse

interface DeleteDeviceRepository {

    fun deleteDevice(deviceId: DeviceId): DeletedDeviceResponse
}