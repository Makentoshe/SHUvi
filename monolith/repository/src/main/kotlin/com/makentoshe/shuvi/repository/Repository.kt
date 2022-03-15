package com.makentoshe.shuvi.repository

import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

// Timestamp pattern
private const val timestampPattern: String = "uuuu-MM-dd'T'HH:mm:ss.SSS O"

private val timestampFormatter = DateTimeFormatter.ofPattern(timestampPattern)

private val timestampZone = ZoneId.of("UTC")

interface Repository {

    val timestamp: String
        get() = ZonedDateTime.now(timestampZone).format(timestampFormatter)
}