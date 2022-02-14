package com.makentoshe.shuvi.database.device

import com.makentoshe.shuvi.entity.database.DatabaseDevice

interface InsertDeviceDatabase {
    fun insertDevice(device: DatabaseDevice)
}