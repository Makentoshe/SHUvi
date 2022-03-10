package com.makentoshe.shuvi.database.crossref

import com.makentoshe.shuvi.common.Either
import com.makentoshe.shuvi.database.Postgres
import com.makentoshe.shuvi.entity.database.crossref.DatabaseDeviceSensorsCrossref
import com.makentoshe.shuvi.entity.database.crossref.DatabaseInsertedDeviceSensorsCrossrefs
import com.makentoshe.shuvi.entity.database.table.DatabaseDeviceSensorsCrossrefTable
import com.makentoshe.shuvi.extension.database.toDatabaseDeviceSensorCrossref
import com.makentoshe.shuvi.response.database.crossref.DatabaseInsertedDeviceSensorsCrossrefsResponse
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.batchInsert

internal class PostgresInsertDeviceSensorsCrossrefDatabase(
    override val database: org.jetbrains.exposed.sql.Database,
) : Postgres(), InsertDeviceSensorsCrossrefDatabase {

    override fun insertCrossrefs(
        crossrefs: List<DatabaseDeviceSensorsCrossref>,
    ): DatabaseInsertedDeviceSensorsCrossrefsResponse = try {
        transaction { transactionInsertCrossrefs(crossrefs) }
    } catch (exception: Exception) {
        Either.Right(exception)
    }

    private fun transactionInsertCrossrefs(
        crossrefs: List<DatabaseDeviceSensorsCrossref>,
    ): DatabaseInsertedDeviceSensorsCrossrefsResponse {
        SchemaUtils.create(DatabaseDeviceSensorsCrossrefTable)

        val insertedCrossrefs = DatabaseDeviceSensorsCrossrefTable.batchInsert(
            data = crossrefs, shouldReturnGeneratedValues = false
        ) { crossref ->
            this[DatabaseDeviceSensorsCrossrefTable.sensorId] = crossref.sensorId
            this[DatabaseDeviceSensorsCrossrefTable.deviceId] = crossref.deviceId
        }.map { record -> record.toDatabaseDeviceSensorCrossref() }

        return Either.Left(DatabaseInsertedDeviceSensorsCrossrefs(insertedCrossrefs))
    }
}