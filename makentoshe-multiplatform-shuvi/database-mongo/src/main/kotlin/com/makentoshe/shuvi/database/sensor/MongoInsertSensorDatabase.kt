package com.makentoshe.shuvi.database.sensor

import com.makentoshe.shuvi.common.Either
import com.makentoshe.shuvi.entity.database.DatabaseSensor
import com.makentoshe.shuvi.response.database.DatabaseInsertedSensorResponse
import com.mongodb.client.MongoCollection

class MongoInsertSensorDatabase(
    private val collection: MongoCollection<DatabaseSensor>,
) : InsertSensorDatabase {
    override fun insert(sensor: DatabaseSensor): DatabaseInsertedSensorResponse {
        return Either.Right(Exception("Stub"))
    }
}