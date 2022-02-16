package com.makentoshe.shuvi.database.device

import com.mongodb.client.MongoDatabase
import org.litote.kmongo.getCollection

internal class MongoDeviceDatabase(private val database: MongoDatabase) : DeviceDatabase {

    override fun get() = MongoGetDevicesDatabase(database.getCollection())

    override fun insert() = MongoInsertDeviceDatabase(database.getCollection())
}
