package com.makentoshe.shuvi.database.crossref

import com.makentoshe.shuvi.common.Either
import com.makentoshe.shuvi.database.Postgres
import com.makentoshe.shuvi.entity.database.crossref.DatabaseDeviceSensorsCrossref
import com.makentoshe.shuvi.entity.database.crossref.DatabaseExistsDeviceSensorsCrossref
import com.makentoshe.shuvi.entity.database.table.DatabaseDeviceSensorsCrossrefTable
import com.makentoshe.shuvi.response.database.crossref.DatabaseExistsDeviceSensorsCrossrefResponse
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.Transaction
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.select

internal class PostgresExistsDeviceSensorsCrossrefDatabase(
    override val database: org.jetbrains.exposed.sql.Database,
) : Postgres(), ExistsDeviceSensorsCrossrefDatabase {

    override fun crossref(crossref: DatabaseDeviceSensorsCrossref): DatabaseExistsDeviceSensorsCrossrefResponse = try {
        transaction { transactionCrossref(crossref) }
    } catch (exception: Exception) {
        Either.Right(exception)
    }

    private fun Transaction.transactionCrossref(
        crossref: DatabaseDeviceSensorsCrossref,
    ): DatabaseExistsDeviceSensorsCrossrefResponse {
        SchemaUtils.create(DatabaseDeviceSensorsCrossrefTable)

        val sensorEq = DatabaseDeviceSensorsCrossrefTable.sensorId eq crossref.sensorId
        val deviceEq = DatabaseDeviceSensorsCrossrefTable.deviceId eq crossref.deviceId
        val record = DatabaseDeviceSensorsCrossrefTable.select {
            sensorEq and deviceEq
        }

        return Either.Left(DatabaseExistsDeviceSensorsCrossref(crossref, record.empty()))
    }
}