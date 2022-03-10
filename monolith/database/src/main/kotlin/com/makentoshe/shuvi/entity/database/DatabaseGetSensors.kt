package com.makentoshe.shuvi.entity.database

import com.makentoshe.shuvi.entity.SensorId

data class DatabaseGetSensors(
    val requestedSensorIds: List<SensorId>,
    val foundSensors: List<DatabaseSensor>,
)