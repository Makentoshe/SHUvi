package com.makentoshe.shuvi.database.device

import com.makentoshe.shuvi.entity.Device
import com.mongodb.client.MongoCollection

internal class MongoGetAllDeviceDatabase(private val collection: MongoCollection<Device>) : GetAllDeviceDatabase {
    override fun getDevices(): List<Device> = collection.find().toList()
}
