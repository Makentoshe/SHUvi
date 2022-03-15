package com.makentoshe.shuvi.service.sensor

import com.makentoshe.shuvi.service.Service
import io.ktor.http.HttpMethod

/**
 * This is a simplified way to post a value from sensor.
 * Here we should put a raw integer value from sensor into the request body.
 * */
interface ValueSensorService : Service {

    override val routing: String
        get() = "api/v1/sensor/{$sensorIdParameter}"

    val sensorIdParameter: String
        get() = "sensorId"

    override val method: HttpMethod
        get() = HttpMethod.Put
}