package com.makentoshe.shuvi.database.crossref

import com.makentoshe.shuvi.common.Either
import com.makentoshe.shuvi.entity.database.crossref.DatabaseDeviceSensorsCrossref
import com.makentoshe.shuvi.entity.database.crossref.DatabaseExistsDeviceSensorsCrossref
import com.makentoshe.shuvi.response.database.crossref.DatabaseExistsDeviceSensorsCrossrefResponse
import com.mongodb.client.MongoCollection
import org.litote.kmongo.eq
import org.litote.kmongo.find

internal class MongoExistsDeviceSensorsCrossrefDatabase(
    private val collection: MongoCollection<DatabaseDeviceSensorsCrossref>,
) : ExistsDeviceSensorsCrossrefDatabase {
    override fun crossref(crossref: DatabaseDeviceSensorsCrossref): DatabaseExistsDeviceSensorsCrossrefResponse = try {
        val deviceIdEq = DatabaseDeviceSensorsCrossref::deviceId eq crossref.deviceId
        val sensorIdEq = DatabaseDeviceSensorsCrossref::sensorId eq crossref.sensorId

        val findIterable = collection.find(filters = arrayOf(deviceIdEq, sensorIdEq))
        Either.Left(DatabaseExistsDeviceSensorsCrossref(crossref, findIterable.contains(crossref)))
    } catch (exception: Exception) {
        Either.Right(exception)
    }
}