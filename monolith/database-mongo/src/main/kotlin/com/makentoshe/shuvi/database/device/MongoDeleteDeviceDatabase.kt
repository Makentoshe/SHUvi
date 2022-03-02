package com.makentoshe.shuvi.database.device

import com.makentoshe.shuvi.common.Either
import com.makentoshe.shuvi.entity.DeviceId
import com.makentoshe.shuvi.entity.database.DatabaseDeletedDevice
import com.makentoshe.shuvi.entity.database.DatabaseDevice
import com.mongodb.client.MongoCollection
import org.litote.kmongo.eq

internal class MongoDeleteDeviceDatabase(
    private val collection: MongoCollection<DatabaseDevice>,
) : DeleteDeviceDatabase {
    override fun id(deviceId: DeviceId) = try {
        val databaseDevice = collection.findOneAndDelete(DatabaseDevice::id eq deviceId.string)
            ?: throw NoSuchElementException(deviceId.string)

        Either.Left(DatabaseDeletedDevice(databaseDevice))
    } catch (exception: Exception) {
        Either.Right(exception)
    }
}