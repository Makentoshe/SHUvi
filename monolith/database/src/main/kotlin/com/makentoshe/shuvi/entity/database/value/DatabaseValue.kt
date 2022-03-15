package com.makentoshe.shuvi.entity.database.value

data class DatabaseValue(
    val id: String,
    val sensorId: String, //TODO might be crossreffed
    val value: String,
    val timestamp: String,
)
