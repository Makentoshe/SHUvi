package com.makentoshe.shuvi.database.device

import com.makentoshe.shuvi.common.Either
import com.makentoshe.shuvi.database.Postgres
import com.makentoshe.shuvi.entity.database.DatabaseDevice
import com.makentoshe.shuvi.entity.database.InsertedDatabaseDevice
import com.makentoshe.shuvi.entity.database.InsertedDatabaseDeviceResponse
import com.makentoshe.shuvi.entity.database.table.DatabaseDeviceTable
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.Transaction
import org.jetbrains.exposed.sql.insert

internal class PostgresInsertDeviceDatabase(
    override val database: org.jetbrains.exposed.sql.Database,
) : Postgres(), InsertDeviceDatabase {

    override fun insertDevice(device: DatabaseDevice): InsertedDatabaseDeviceResponse = try {
        transaction { transactionInsert(device) }
    } catch (exception: Exception) {
        Either.Right(exception)
    }

    private fun Transaction.transactionInsert(device: DatabaseDevice): InsertedDatabaseDeviceResponse {
        SchemaUtils.create(DatabaseDeviceTable)

        val record = DatabaseDeviceTable.insert {
            it[id] = device.id
            it[title] = device.title
        }

        if (record.insertedCount != 1) throw Exception("$device wasn't inserted")

        return Either.Left(InsertedDatabaseDevice(device))
    }

}