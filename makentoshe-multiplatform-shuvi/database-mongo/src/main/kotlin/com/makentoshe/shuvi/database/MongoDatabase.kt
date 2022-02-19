package com.makentoshe.shuvi.database

import com.makentoshe.shuvi.database.crossref.CrossrefDatabase
import com.makentoshe.shuvi.database.crossref.MongoCrossrefDatabase
import com.makentoshe.shuvi.database.device.DeviceDatabase
import com.makentoshe.shuvi.database.device.MongoDeviceDatabase
import com.makentoshe.shuvi.database.sensor.MongoSensorDatabase
import com.makentoshe.shuvi.database.sensor.SensorDatabase
import com.mongodb.client.MongoClient
import org.litote.kmongo.KMongo

class MongoDatabase(private val client: MongoClient = KMongo.createClient()) : Database {

    private val title = "DevelopShuviDatabase"

    override fun device(): DeviceDatabase {
        return MongoDeviceDatabase(client.getDatabase(title))
    }

    override fun sensor(): SensorDatabase {
        return MongoSensorDatabase(client.getDatabase(title))
    }

    override fun crossref(): CrossrefDatabase {
        return MongoCrossrefDatabase(client.getDatabase(title))
    }
}
