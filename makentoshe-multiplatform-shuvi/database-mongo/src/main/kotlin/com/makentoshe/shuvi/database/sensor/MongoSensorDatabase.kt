package com.makentoshe.shuvi.database.sensor

import com.mongodb.client.MongoDatabase
import org.litote.kmongo.getCollection

class MongoSensorDatabase(private val database: MongoDatabase) : SensorDatabase {
    override fun insert() = MongoInsertSensorDatabase(database.getCollection())
}