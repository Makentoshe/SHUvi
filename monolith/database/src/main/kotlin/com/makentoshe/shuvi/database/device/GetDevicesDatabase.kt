package com.makentoshe.shuvi.database.device

import com.makentoshe.shuvi.entity.DeviceId
import com.makentoshe.shuvi.response.database.DatabaseGetDeviceResponse
import com.makentoshe.shuvi.response.database.DatabaseGetDevicesResponse

/** Allows to request devices from database */
interface GetDevicesDatabase {

    /** Returns all devices */
    fun all(): DatabaseGetDevicesResponse

    /** Returns device by its DeviceId */
    fun id(deviceId: DeviceId): DatabaseGetDeviceResponse
}