package com.makentoshe.shuvi.database.device

import com.makentoshe.shuvi.entity.database.DatabaseDevice
import com.mongodb.client.MongoCollection

internal class MongoGetAllDeviceDatabase(
    private val collection: MongoCollection<DatabaseDevice>,
) : GetAllDeviceDatabase {
    override fun getDevices(): List<DatabaseDevice> = collection.find().toList()
}
