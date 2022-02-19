package com.makentoshe.shuvi.database

import com.makentoshe.shuvi.database.crossref.CrossrefDatabase
import com.makentoshe.shuvi.database.device.DeviceDatabase
import com.makentoshe.shuvi.database.sensor.SensorDatabase

interface Database {
    /** Database for any connected devices */
    fun device() : DeviceDatabase

    /** Database for any sensor devices */
    fun sensor() : SensorDatabase

    /** Database for cross-references between entities */
    fun crossref(): CrossrefDatabase
}