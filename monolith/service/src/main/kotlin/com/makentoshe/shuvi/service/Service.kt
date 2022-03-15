package com.makentoshe.shuvi.service

import io.ktor.application.ApplicationCall
import io.ktor.http.HttpMethod

interface Service {
    val routing: String

    val method: HttpMethod
        get() = HttpMethod.Get

    suspend fun handle(call: ApplicationCall) = handle(ServiceCall(call))

    suspend fun handle(call: ServiceCall) = Unit
}
