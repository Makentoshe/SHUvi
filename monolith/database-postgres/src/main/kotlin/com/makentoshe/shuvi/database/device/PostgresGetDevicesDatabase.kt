package com.makentoshe.shuvi.database.device

import com.makentoshe.shuvi.common.Either
import com.makentoshe.shuvi.database.Postgres
import com.makentoshe.shuvi.entity.DeviceId
import com.makentoshe.shuvi.entity.database.DatabaseDevice
import com.makentoshe.shuvi.entity.database.table.DatabaseDeviceTable
import com.makentoshe.shuvi.extension.database.toDatabaseDevice
import com.makentoshe.shuvi.response.database.DatabaseGetDeviceResponse
import com.makentoshe.shuvi.response.database.DatabaseGetDevicesResponse
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Transaction
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll

internal class PostgresGetDevicesDatabase(
    override val database: org.jetbrains.exposed.sql.Database,
) : Postgres(), GetDevicesDatabase {

    override fun all(): DatabaseGetDevicesResponse = try {
        transaction { transactionAll() }
    } catch (exception: Exception) {
        Either.Right(exception)
    }

    override fun id(deviceId: DeviceId): DatabaseGetDeviceResponse = try {
        transaction { transactionId(deviceId.string) }
    } catch (exception: Exception) {
        Either.Right(exception)
    }

    private fun Transaction.transactionAll(): DatabaseGetDevicesResponse {
        return Either.Left(DatabaseDeviceTable.selectAll().toList().map { record -> record.toDatabaseDevice() })
    }

    private fun Transaction.transactionId(deviceId: String): DatabaseGetDeviceResponse {
        val record = DatabaseDeviceTable.select {
            DatabaseDeviceTable.id eq deviceId
        }.firstOrNull() ?: throw Exception("Not found Device with id $deviceId")

        return Either.Left(record.toDatabaseDevice())
    }
}