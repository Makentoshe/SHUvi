package com.makentoshe.shuvi.service.value

import com.makentoshe.shuvi.service.Service
import io.ktor.http.HttpMethod

interface GetValueService : Service {

    override val routing: String
        get() = "api/v1/values.json"

    val limitQuery: String
        get() = "limit"

    override val method: HttpMethod
        get() = HttpMethod.Get
}