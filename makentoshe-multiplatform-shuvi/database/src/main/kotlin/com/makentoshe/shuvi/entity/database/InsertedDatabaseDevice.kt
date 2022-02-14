package com.makentoshe.shuvi.entity.database

import com.makentoshe.shuvi.common.Either

typealias InsertedDatabaseDeviceResponse = Either<InsertedDatabaseDevice, Exception>

data class InsertedDatabaseDevice(
    val deviceDatabase: DatabaseDevice,
    val wasAcknowledged: Boolean,
)