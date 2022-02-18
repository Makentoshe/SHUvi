package com.makentoshe.shuvi.database.sensor

/**
 * Database that may perform any actions with Sensor entities.
 */
interface SensorDatabase {

    fun get(): GetSensorDatabase

    fun insert(): InsertSensorDatabase
}
