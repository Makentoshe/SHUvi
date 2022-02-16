package com.makentoshe.shuvi.database.device

import com.makentoshe.shuvi.common.Either
import com.makentoshe.shuvi.entity.database.DatabaseDevice
import com.makentoshe.shuvi.response.database.DatabaseGetDevicesResponse
import com.mongodb.client.MongoCollection

internal class MongoGetDevicesDatabase(
    private val collection: MongoCollection<DatabaseDevice>,
) : GetDevicesDatabase {
    override fun getDevices(): DatabaseGetDevicesResponse = try {
        Either.Left(collection.find().toList())
    } catch (e: Exception) {
        Either.Right(e)
    }
}
