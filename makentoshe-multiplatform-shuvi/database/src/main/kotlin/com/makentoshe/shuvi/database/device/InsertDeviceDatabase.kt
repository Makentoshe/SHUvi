package com.makentoshe.shuvi.database.device

import com.makentoshe.shuvi.entity.database.DatabaseDevice
import com.makentoshe.shuvi.entity.database.InsertedDatabaseDeviceResponse

interface InsertDeviceDatabase {
    fun insertDevice(device: DatabaseDevice): InsertedDatabaseDeviceResponse
}