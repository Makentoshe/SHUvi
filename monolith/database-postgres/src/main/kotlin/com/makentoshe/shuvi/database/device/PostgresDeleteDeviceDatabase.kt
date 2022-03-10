package com.makentoshe.shuvi.database.device

import com.makentoshe.shuvi.common.Either
import com.makentoshe.shuvi.database.Postgres
import com.makentoshe.shuvi.entity.DeviceId
import com.makentoshe.shuvi.entity.database.DatabaseDeletedDevice
import com.makentoshe.shuvi.entity.database.table.DatabaseDeviceTable
import com.makentoshe.shuvi.extension.database.toDatabaseDevice
import com.makentoshe.shuvi.response.database.DatabaseDeletedDeviceResponse
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.Transaction
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.select

internal class PostgresDeleteDeviceDatabase(
    override val database: org.jetbrains.exposed.sql.Database,
) : Postgres(), DeleteDeviceDatabase {

    override fun id(deviceId: DeviceId): DatabaseDeletedDeviceResponse = try {
        transaction { transactionId(deviceId.string) }
    } catch (exception: Exception) {
        Either.Right(exception)
    }

    private fun Transaction.transactionId(deviceId: String): DatabaseDeletedDeviceResponse {
        SchemaUtils.create(DatabaseDeviceTable)

        val record = DatabaseDeviceTable.select {
            DatabaseDeviceTable.id eq deviceId
        }.firstOrNull() ?: throw Exception("Not found Device with id $deviceId")

        if (DatabaseDeviceTable.deleteWhere { DatabaseDeviceTable.id eq deviceId } != 1) {
            throw Exception("Could not delete device with id $deviceId")
        }

        return Either.Left(DatabaseDeletedDevice(record.toDatabaseDevice()))
    }
}