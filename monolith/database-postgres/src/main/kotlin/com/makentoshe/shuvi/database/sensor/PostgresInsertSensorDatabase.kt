package com.makentoshe.shuvi.database.sensor

import com.makentoshe.shuvi.common.Either
import com.makentoshe.shuvi.entity.database.table.DatabaseSensorTable
import com.makentoshe.shuvi.database.Postgres
import com.makentoshe.shuvi.entity.database.DatabaseInsertedSensor
import com.makentoshe.shuvi.entity.database.DatabaseSensor
import com.makentoshe.shuvi.response.database.DatabaseInsertedSensorResponse
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.Transaction
import org.jetbrains.exposed.sql.insert

internal class PostgresInsertSensorDatabase(override val database: Database) : Postgres(), InsertSensorDatabase {

    override fun insertSensor(sensor: DatabaseSensor): DatabaseInsertedSensorResponse = try {
        transaction { insertSensorTransaction(sensor) }
    } catch (exception: Exception) {
        Either.Right(exception)
    }

    private fun Transaction.insertSensorTransaction(sensor: DatabaseSensor): DatabaseInsertedSensorResponse {
        SchemaUtils.create(DatabaseSensorTable)

        val record = DatabaseSensorTable.insert {
            it[id] = sensor.id
            it[title] = sensor.title
        }

        if (record.insertedCount != 1) throw Exception("$sensor wasn't inserted")

        return Either.Left(DatabaseInsertedSensor(sensor))
    }
}