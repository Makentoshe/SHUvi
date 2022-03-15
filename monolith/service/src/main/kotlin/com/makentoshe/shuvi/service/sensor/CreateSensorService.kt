package com.makentoshe.shuvi.service.sensor

import com.makentoshe.shuvi.service.Service
import io.ktor.http.HttpMethod

interface CreateSensorService : Service {

    override val routing: String
        get() = "api/v1/sensor/create"

    override val method: HttpMethod
        get() = HttpMethod.Post
}