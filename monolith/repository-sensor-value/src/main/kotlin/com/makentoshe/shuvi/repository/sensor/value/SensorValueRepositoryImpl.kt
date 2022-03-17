package com.makentoshe.shuvi.repository.sensor.value

import com.makentoshe.shuvi.common.Either
import com.makentoshe.shuvi.common.database.ValueIdGenerator
import com.makentoshe.shuvi.database.Database
import com.makentoshe.shuvi.entity.database.value.DatabaseValue
import com.makentoshe.shuvi.entity.database.value.toValue
import com.makentoshe.shuvi.entity.sensor.value.CreateSensorValue
import com.makentoshe.shuvi.entity.sensor.value.CreatedSensorValue
import com.makentoshe.shuvi.response.repository.sensor.value.CreateSensorValueResponse

class SensorValueRepositoryImpl(
    private val database: Database,
    private val generator: ValueIdGenerator,
) : SensorValueRepository {

    /** Return true if sensor exists and false otherwise */
    private val CreateSensorValue.isExists: Boolean
        get() = valueId?.let { database.value().get().id(it).isLeft() } == true

    override fun create(value: CreateSensorValue): CreateSensorValueResponse {
        if (value.isExists) return Either.Right(Exception("${value.valueId} is already exists"))

        val databaseValue = DatabaseValue(value.valueId ?: generator.generate, timestamp, value)

        return database.value().insert().value(databaseValue).mapLeft { databaseInsertedValue ->
            CreatedSensorValue(value, databaseInsertedValue.value.toValue())
        }
    }
}