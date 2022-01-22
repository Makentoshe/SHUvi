package com.makentoshe.shuvi.database.device

import com.makentoshe.shuvi.entity.database.DatabaseDevice

interface GetAllDeviceDatabase {
    fun getDevices(): List<DatabaseDevice>
}