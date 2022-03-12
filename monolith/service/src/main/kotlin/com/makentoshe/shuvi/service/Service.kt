package com.makentoshe.shuvi.service

import io.ktor.application.ApplicationCall

interface Service {
    val routing: String

    suspend fun handle(call: ApplicationCall) = handle(ServiceCall(call))

    suspend fun handle(call: ServiceCall) = Unit
}