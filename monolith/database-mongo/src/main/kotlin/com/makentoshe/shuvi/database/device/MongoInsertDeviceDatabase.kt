package com.makentoshe.shuvi.database.device

import com.makentoshe.shuvi.common.Either
import com.makentoshe.shuvi.entity.database.DatabaseDevice
import com.makentoshe.shuvi.entity.database.InsertedDatabaseDevice
import com.makentoshe.shuvi.entity.database.InsertedDatabaseDeviceResponse
import com.mongodb.client.MongoCollection

internal class MongoInsertDeviceDatabase(
    private val collection: MongoCollection<DatabaseDevice>,
) : InsertDeviceDatabase {

    override fun insertDevice(device: DatabaseDevice): InsertedDatabaseDeviceResponse = try {
        if (!collection.insertOne(device).wasAcknowledged()) throw Exception("Insertion wasn't acknowledged")
        Either.Left(InsertedDatabaseDevice(device))
    } catch (exception: Exception) {
        Either.Right(exception)
    }
}