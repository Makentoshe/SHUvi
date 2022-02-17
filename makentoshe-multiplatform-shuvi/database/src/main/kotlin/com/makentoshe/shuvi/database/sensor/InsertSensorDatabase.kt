package com.makentoshe.shuvi.database.sensor

import com.makentoshe.shuvi.entity.database.DatabaseSensor
import com.makentoshe.shuvi.response.database.DatabaseInsertedSensorResponse

interface InsertSensorDatabase {
    fun insert(sensor: DatabaseSensor): DatabaseInsertedSensorResponse
}
