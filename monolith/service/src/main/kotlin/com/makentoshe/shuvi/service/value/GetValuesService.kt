package com.makentoshe.shuvi.service.value

import com.makentoshe.shuvi.service.Service
import io.ktor.http.HttpMethod

interface GetValuesService : Service {

    override val routing: String
        get() = "api/v1/values"

    /** Optional query allows defining how many items should be returned */
    val limitQuery: String
        get() = "limit"

    val sensorQuery: String
        get() = "sensor"

    override val method: HttpMethod
        get() = HttpMethod.Get
}