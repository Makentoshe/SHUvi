package com.makentoshe.shuvi.database

import com.makentoshe.shuvi.database.device.DeviceDatabase

interface Database {
    fun device() : DeviceDatabase
}