package com.makentoshe.shuvi.service.sensor

import com.makentoshe.shuvi.service.Service

interface GetSensorService : Service {

    override val routing: String
        get() = "api/v1/sensor/{$sensorIdParameter}.json"

    val sensorIdParameter: String
        get() = "sensorId"
}