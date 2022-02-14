package com.makentoshe.shuvi.database.device

import com.makentoshe.shuvi.entity.database.DatabaseDevice
import com.mongodb.MongoWriteException
import com.mongodb.client.MongoCollection

internal class MongoInsertDeviceDatabase(
    private val collection: MongoCollection<DatabaseDevice>,
) : InsertDeviceDatabase {
    override fun insertDevice(device: DatabaseDevice) = try {
        println("Insert $device")
        println("Result ${collection.insertOne(device)}")
    } catch (writeException: MongoWriteException) {
        println(writeException)
    }
}