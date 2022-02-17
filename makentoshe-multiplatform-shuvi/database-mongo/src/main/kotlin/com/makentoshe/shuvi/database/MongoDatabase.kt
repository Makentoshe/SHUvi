package com.makentoshe.shuvi.database

import com.makentoshe.shuvi.database.device.DeviceDatabase
import com.makentoshe.shuvi.database.device.MongoDeviceDatabase
import com.makentoshe.shuvi.database.sensor.MongoSensorDatabase
import com.makentoshe.shuvi.database.sensor.SensorDatabase
import com.mongodb.client.MongoClient
import org.litote.kmongo.KMongo

class MongoDatabase(private val client: MongoClient = KMongo.createClient()) : Database {

    override fun device(): DeviceDatabase {
        return MongoDeviceDatabase(client.getDatabase("Devices"))
    }

    override fun sensor(): SensorDatabase {
        return MongoSensorDatabase(client.getDatabase("Sensors"))
    }
}
