package com.makentoshe.shuvi.database.sensor

import com.makentoshe.shuvi.entity.SensorId
import com.makentoshe.shuvi.response.database.DatabaseGetSensorResponse
import com.makentoshe.shuvi.response.database.DatabaseGetSensorsResponse

interface GetSensorDatabase {
    fun id(sensorId: SensorId): DatabaseGetSensorResponse

    fun ids(sensorIds: List<SensorId>): DatabaseGetSensorsResponse
}