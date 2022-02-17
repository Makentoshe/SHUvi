package com.makentoshe.shuvi.database

import com.makentoshe.shuvi.database.device.DeviceDatabase
import com.makentoshe.shuvi.database.sensor.SensorDatabase

interface Database {
    /** Database for any connected devices */
    fun device() : DeviceDatabase

    /** database for any sensor devices */
    fun sensor() : SensorDatabase
}