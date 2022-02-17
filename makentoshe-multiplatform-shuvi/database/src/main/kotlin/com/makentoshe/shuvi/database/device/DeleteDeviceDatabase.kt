package com.makentoshe.shuvi.database.device

import com.makentoshe.shuvi.entity.database.DatabaseDeviceId
import com.makentoshe.shuvi.response.database.DatabaseDeletedDeviceResponse

/** Allows deleting device from the database */
interface DeleteDeviceDatabase {
    /** Delete device by its id */
    fun id(deviceId: DatabaseDeviceId) : DatabaseDeletedDeviceResponse
}