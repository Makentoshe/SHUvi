package com.makentoshe.shuvi.database.crossref

import com.makentoshe.shuvi.common.Either
import com.makentoshe.shuvi.entity.DeviceId
import com.makentoshe.shuvi.entity.database.crossref.DatabaseDeviceSensorsCrossref
import com.makentoshe.shuvi.response.database.crossref.DatabaseGetDeviceSensorsCrossrefResponse
import com.mongodb.client.MongoCollection
import org.litote.kmongo.eq

internal class MongoGetDeviceSensorsCrossrefDatabase(
    private val collection: MongoCollection<DatabaseDeviceSensorsCrossref>,
) : GetDeviceSensorsCrossrefDatabase {
    override fun deviceId(deviceId: DeviceId): DatabaseGetDeviceSensorsCrossrefResponse = try {
        val findIterable = collection.find(DatabaseDeviceSensorsCrossref::deviceId eq deviceId.string)
        println(findIterable.toList())

        Either.Left(findIterable.toList())
    } catch (exception: Exception) {
        Either.Right(exception)
    }
}