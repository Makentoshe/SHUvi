package com.makentoshe.shuvi.database.crossref


/**
 * Database that may perform any actions with cross-references between entities
 */
interface CrossrefDatabase {

    /** Database for cross-references between Device and Sensor with many-2-many relations */
    fun device2Sensors(): DeviceSensorsCrossrefDatabase
}

