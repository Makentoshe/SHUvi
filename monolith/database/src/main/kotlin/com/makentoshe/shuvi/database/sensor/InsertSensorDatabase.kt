package com.makentoshe.shuvi.database.sensor

import com.makentoshe.shuvi.entity.database.DatabaseSensor
import com.makentoshe.shuvi.response.database.DatabaseInsertedSensorResponse

interface InsertSensorDatabase {
    fun insertSensor(sensor: DatabaseSensor): DatabaseInsertedSensorResponse
}
