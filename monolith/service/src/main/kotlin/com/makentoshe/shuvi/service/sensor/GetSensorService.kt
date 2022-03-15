package com.makentoshe.shuvi.service.sensor

import com.makentoshe.shuvi.service.Service
import io.ktor.http.HttpMethod

interface GetSensorService : Service {

    override val routing: String
        get() = "api/v1/sensor/{$sensorIdParameter}.json"

    val sensorIdParameter: String
        get() = "sensorId"

    override val method: HttpMethod
        get() = HttpMethod.Get
}