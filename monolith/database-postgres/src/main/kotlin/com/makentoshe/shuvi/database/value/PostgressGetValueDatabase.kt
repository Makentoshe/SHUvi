package com.makentoshe.shuvi.database.value

import com.makentoshe.shuvi.common.Either
import com.makentoshe.shuvi.database.Postgres
import com.makentoshe.shuvi.entity.SensorId
import com.makentoshe.shuvi.entity.database.table.DatabaseValueTable
import com.makentoshe.shuvi.entity.database.value.DatabaseValue
import com.makentoshe.shuvi.entity.sensor.value.SensorValueId
import com.makentoshe.shuvi.extension.database.toDatabaseValue
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.Transaction
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll

class PostgressGetValueDatabase(override val database: Database) : Postgres(), GetValueDatabase {

    override fun id(value: SensorValueId): DatabaseGetValueResponse = safeEither {
        transaction { transactionId(value) }
    }

    override fun all(limit: Int): DatabaseGetAllValuesResponse = safeEither {
        transaction { transactionAll(limit) }
    }

    override fun sensor(sensorId: SensorId, limit: Int): DatabaseGetSensorValuesResponse = safeEither {
        transaction { transactionSensor(sensorId, limit) }
    }

    private fun Transaction.transactionId(value: SensorValueId): DatabaseGetValueResponse {
        SchemaUtils.create(DatabaseValueTable)

        val record = DatabaseValueTable.select {
            DatabaseValueTable.valueId eq value.string
        }.firstOrNull() ?: throw Exception("Not found Value with id $value")

        return Either.Left(record.toDatabaseValue())
    }

    private fun Transaction.transactionAll(limit: Int): DatabaseGetAllValuesResponse {
        SchemaUtils.create(DatabaseValueTable)

        val record = DatabaseValueTable.selectAll().limit(limit)

        return Either.Left(record.toList().map { resultRow -> resultRow.toDatabaseValue() })
    }

    private fun Transaction.transactionSensor(sensorId: SensorId, limit: Int): Either.Left<List<DatabaseValue>> {
        SchemaUtils.create(DatabaseValueTable)

        val record = DatabaseValueTable.select {
            DatabaseValueTable.sensorId eq sensorId.string
        }.limit(limit)

        return Either.Left(record.toList().map { resultRow -> resultRow.toDatabaseValue() })
    }
}