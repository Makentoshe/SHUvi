package com.makentoshe.shuvi.database.sensor

import com.makentoshe.shuvi.common.Either
import com.makentoshe.shuvi.entity.database.DatabaseSensor
import com.makentoshe.shuvi.response.database.DatabaseInsertedSensorResponse
import com.mongodb.client.MongoCollection

internal class MongoInsertSensorDatabase(
    private val collection: MongoCollection<DatabaseSensor>,
) : InsertSensorDatabase {
    override fun insertSensor(sensor: DatabaseSensor): DatabaseInsertedSensorResponse {
        return Either.Right(Exception("$sensor should be inserted here"))
    }
}