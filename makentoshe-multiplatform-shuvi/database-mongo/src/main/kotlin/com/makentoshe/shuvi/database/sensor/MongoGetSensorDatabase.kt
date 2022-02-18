package com.makentoshe.shuvi.database.sensor

import com.makentoshe.shuvi.common.Either
import com.makentoshe.shuvi.entity.SensorId
import com.makentoshe.shuvi.entity.database.DatabaseGetSensors
import com.makentoshe.shuvi.entity.database.DatabaseSensor
import com.makentoshe.shuvi.response.database.DatabaseGetSensorsResponse
import com.mongodb.client.MongoCollection
import org.litote.kmongo.*

internal class MongoGetSensorDatabase(
    private val collection: MongoCollection<DatabaseSensor>,
) : GetSensorDatabase {
    override fun id(sensorId: SensorId) = try {
        val filter = DatabaseSensor::id eq sensorId.string
        val databaseSensor = collection.aggregate<DatabaseSensor>(match(filter), sample(1)).first()
        Either.Left(databaseSensor ?: throw NoSuchElementException("No elements with $sensorId"))
    } catch (exception: Exception) {
        Either.Right(exception)
    }

    override fun ids(sensorIds: List<SensorId>): DatabaseGetSensorsResponse = try {
        val foundSensors = sensorIds.map { collection.find(DatabaseSensor::id eq it.string).first() }
        Either.Left(DatabaseGetSensors(sensorIds, foundSensors.mapNotNull { it?.toSensor() }))
    } catch (exception: Exception) {
        Either.Right(exception)
    }
}