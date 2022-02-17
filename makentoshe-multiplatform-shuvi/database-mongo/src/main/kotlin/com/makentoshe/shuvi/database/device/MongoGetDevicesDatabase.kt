package com.makentoshe.shuvi.database.device

import com.makentoshe.shuvi.common.Either
import com.makentoshe.shuvi.entity.DeviceId
import com.makentoshe.shuvi.entity.database.DatabaseDevice
import com.makentoshe.shuvi.response.database.DatabaseGetDeviceResponse
import com.makentoshe.shuvi.response.database.DatabaseGetDevicesResponse
import com.mongodb.client.MongoCollection
import org.litote.kmongo.*

internal class MongoGetDevicesDatabase(
    private val collection: MongoCollection<DatabaseDevice>,
) : GetDevicesDatabase {
    override fun all(): DatabaseGetDevicesResponse = try {
        Either.Left(collection.find().toList())
    } catch (e: Exception) {
        Either.Right(e)
    }

    override fun id(deviceId: DeviceId): DatabaseGetDeviceResponse = try {
        val filter = DatabaseDevice::id eq deviceId.string
        val databaseDevice = collection.aggregate<DatabaseDevice>(match(filter), sample(1)).first()
        Either.Left(databaseDevice ?: throw NoSuchElementException("No elements with $deviceId"))
    } catch (e: Exception) {
        Either.Right(e)
    }
}
