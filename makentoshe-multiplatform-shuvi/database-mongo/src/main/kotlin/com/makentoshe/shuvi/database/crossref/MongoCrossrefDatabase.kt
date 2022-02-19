package com.makentoshe.shuvi.database.crossref

import com.mongodb.client.MongoDatabase
import org.litote.kmongo.getCollection

internal class MongoCrossrefDatabase(private val database: MongoDatabase) : CrossrefDatabase {
    override fun device2Sensors(): DeviceSensorsCrossrefDatabase {
        return MongoDeviceSensorsCrossrefDatabase(database.getCollection())
    }
}

