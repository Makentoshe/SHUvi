package com.makentoshe.shuvi.database.sensor

import com.makentoshe.shuvi.common.Either
import com.makentoshe.shuvi.database.Postgres
import com.makentoshe.shuvi.entity.SensorId
import com.makentoshe.shuvi.entity.database.DatabaseGetSensors
import com.makentoshe.shuvi.entity.database.DatabaseSensor
import com.makentoshe.shuvi.entity.database.table.DatabaseSensorTable
import com.makentoshe.shuvi.extension.database.toDatabaseSensor
import com.makentoshe.shuvi.response.database.DatabaseGetSensorResponse
import com.makentoshe.shuvi.response.database.DatabaseGetSensorsResponse
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.Transaction
import org.jetbrains.exposed.sql.select

internal class PostgresGetSensorDatabase(override val database: Database) : Postgres(), GetSensorDatabase {

    override fun id(sensorId: SensorId): DatabaseGetSensorResponse = try {
        transaction { transactionId(sensorId.string) }
    } catch (exception: Exception) {
        Either.Right(exception)
    }

    override fun ids(sensorIds: List<SensorId>): DatabaseGetSensorsResponse = try {
        transaction { transactionIds(sensorIds) }
    } catch (exception: Exception) {
        Either.Right(exception)
    }

    private fun Transaction.transactionId(sensorId: String): DatabaseGetSensorResponse {
        SchemaUtils.create(DatabaseSensorTable)

        val record = DatabaseSensorTable.select {
            DatabaseSensorTable.id eq sensorId
        }.firstOrNull() ?: throw Exception("Not found Sensor with id $sensorId")

        return Either.Left(record.toDatabaseSensor())
    }

    private fun Transaction.transactionIds(sensorIds: List<SensorId>): DatabaseGetSensorsResponse {
        SchemaUtils.create(DatabaseSensorTable)

        val records = DatabaseSensorTable.select {
            DatabaseSensorTable.id inList sensorIds.map { sensorId -> sensorId.string }
        }

        return Either.Left(DatabaseGetSensors(sensorIds, records.toList().map { record -> record.toDatabaseSensor() }))
    }

}