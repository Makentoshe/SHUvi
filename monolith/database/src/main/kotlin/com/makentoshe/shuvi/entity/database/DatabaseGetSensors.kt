package com.makentoshe.shuvi.entity.database

import com.makentoshe.shuvi.entity.Sensor
import com.makentoshe.shuvi.entity.SensorId

data class DatabaseGetSensors(
    val requestedSensorIds: List<SensorId>,
    val foundSensors: List<Sensor>,
)