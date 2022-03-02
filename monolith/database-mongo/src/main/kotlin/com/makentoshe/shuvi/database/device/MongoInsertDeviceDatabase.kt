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
        val insertOneResult = collection.insertOne(device)
        Either.Left(InsertedDatabaseDevice(device, insertOneResult.wasAcknowledged()))
    } catch (exception: Exception) {
        Either.Right(exception)
    }
}