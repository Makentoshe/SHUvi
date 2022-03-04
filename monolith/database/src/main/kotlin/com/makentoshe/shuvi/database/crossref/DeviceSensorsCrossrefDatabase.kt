package com.makentoshe.shuvi.database.crossref

/** Database for cross-references between Device and Sensor with one-2-many relations */
interface DeviceSensorsCrossrefDatabase {

    fun get(): GetDeviceSensorsCrossrefDatabase

    fun insert(): InsertDeviceSensorsCrossrefDatabase

    fun exists(): ExistsDeviceSensorsCrossrefDatabase
}
