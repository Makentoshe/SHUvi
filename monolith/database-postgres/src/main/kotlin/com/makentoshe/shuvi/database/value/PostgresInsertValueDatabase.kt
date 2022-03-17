package com.makentoshe.shuvi.database.value

import com.makentoshe.shuvi.common.Either
import com.makentoshe.shuvi.database.Postgres
import com.makentoshe.shuvi.entity.CreateSensor
import com.makentoshe.shuvi.entity.database.table.DatabaseValueTable
import com.makentoshe.shuvi.entity.database.value.DatabaseInsertedValue
import com.makentoshe.shuvi.entity.database.value.DatabaseValue
import com.makentoshe.shuvi.response.database.value.DatabaseInsertValueResponse
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.Transaction
import org.jetbrains.exposed.sql.insert

class PostgresInsertValueDatabase(override val database: Database): Postgres(), InsertValueDatabase {

    override fun value(value: DatabaseValue): DatabaseInsertValueResponse = try {
        transaction { transactionValue(value) }
    } catch (exception: Exception) {
        Either.Right(exception)
    }

    private fun Transaction.transactionValue(value: DatabaseValue) : DatabaseInsertValueResponse {
        SchemaUtils.create(DatabaseValueTable)

        val record = DatabaseValueTable.insert {
            it[DatabaseValueTable.valueId] = value.valueId
            it[DatabaseValueTable.sensorId] = value.sensorId
            it[DatabaseValueTable.value] = value.value
            it[DatabaseValueTable.timestamp] = value.timestamp
        }

        if (record.insertedCount != 1) throw Exception("$value wasn't inserted")

        return Either.Left(DatabaseInsertedValue(value))
    }
}