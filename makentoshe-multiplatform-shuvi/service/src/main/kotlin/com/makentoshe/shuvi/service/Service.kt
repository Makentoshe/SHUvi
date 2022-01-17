package com.makentoshe.shuvi.service

import io.ktor.application.*

interface Service {
    val routing: String

    suspend fun handle(call: ApplicationCall)
}