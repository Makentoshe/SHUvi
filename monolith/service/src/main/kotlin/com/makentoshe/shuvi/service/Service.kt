package com.makentoshe.shuvi.service

import io.ktor.application.ApplicationCall
import io.ktor.http.HttpMethod
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

// Timestamp pattern
private const val timestampPattern: String = "uuuu-MM-dd'T'HH:mm:ss.SSS O"

private val timestampFormatter = DateTimeFormatter.ofPattern(timestampPattern)

private val timestampZone = ZoneId.of("UTC")

interface Service {
    val routing: String

    val method: HttpMethod
        get() = HttpMethod.Get

    suspend fun handle(call: ApplicationCall) = handle(ServiceCall(call))

    suspend fun handle(call: ServiceCall) = Unit
}

val Service.timestamp: String
    get() = ZonedDateTime.now(timestampZone).format(timestampFormatter)
