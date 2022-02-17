package com.makentoshe.shuvi.entity.database

import com.makentoshe.shuvi.common.Either

typealias InsertedDatabaseDeviceResponse = Either<InsertedDatabaseDevice, Exception>

/** Inserted device into the database */
data class InsertedDatabaseDevice(
    val deviceDatabase: DatabaseDevice,
    val wasAcknowledged: Boolean,
)