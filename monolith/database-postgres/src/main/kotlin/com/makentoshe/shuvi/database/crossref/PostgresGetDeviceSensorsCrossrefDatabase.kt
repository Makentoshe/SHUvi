package com.makentoshe.shuvi.database.crossref

import com.makentoshe.shuvi.common.Either
import com.makentoshe.shuvi.database.Postgres
import com.makentoshe.shuvi.entity.DeviceId
import com.makentoshe.shuvi.entity.database.table.DatabaseDeviceSensorsCrossrefTable
import com.makentoshe.shuvi.extension.database.toDatabaseDeviceSensorCrossref
import com.makentoshe.shuvi.response.database.crossref.DatabaseGetDeviceSensorsCrossrefResponse
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.Transaction
import org.jetbrains.exposed.sql.select

internal class PostgresGetDeviceSensorsCrossrefDatabase(
    override val database: org.jetbrains.exposed.sql.Database,
) : Postgres(), GetDeviceSensorsCrossrefDatabase {

    override fun deviceId(deviceId: DeviceId): DatabaseGetDeviceSensorsCrossrefResponse = try {
        transaction { transactionDeviceId(deviceId.string) }
    } catch (exception: Exception) {
        Either.Right(exception)
    }

    private fun Transaction.transactionDeviceId(deviceId: String): DatabaseGetDeviceSensorsCrossrefResponse {
        SchemaUtils.create(DatabaseDeviceSensorsCrossrefTable)

        val record = DatabaseDeviceSensorsCrossrefTable.select {
            DatabaseDeviceSensorsCrossrefTable.deviceId eq deviceId
        }.toList().map { it.toDatabaseDeviceSensorCrossref() }

        return Either.Left(record)
    }
}