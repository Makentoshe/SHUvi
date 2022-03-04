package com.makentoshe.shuvi.repository.sensor.create

import com.makentoshe.shuvi.common.Either
import com.makentoshe.shuvi.common.database.IdGenerator
import com.makentoshe.shuvi.common.database.SensorIdGenerator
import com.makentoshe.shuvi.database.Database
import com.makentoshe.shuvi.entity.CreateSensor
import com.makentoshe.shuvi.entity.CreatedSensor
import com.makentoshe.shuvi.entity.SensorId
import com.makentoshe.shuvi.entity.database.DatabaseSensor
import com.makentoshe.shuvi.entity.sensor.create.CreateSensorWithId
import com.makentoshe.shuvi.entity.sensor.create.toCreateSensorWithId
import com.makentoshe.shuvi.repository.sensor.CreateSensorRepository
import com.makentoshe.shuvi.response.repository.sensor.CreateSensorResponse
import org.apache.commons.lang3.RandomStringUtils

class CreateSensorRepositoryImpl(
    private val database: Database,
    private val generator: SensorIdGenerator,
) : CreateSensorRepository {

    /** Return true if sensor exists and false otherwise */
    private val CreateSensor.isExists: Boolean get() = id?.let { database.sensor().get().id(it).isLeft() } == true

    override fun create(sensor: CreateSensor) = try {
        internalCreate(sensor)
    } catch (exception: Exception) {
        Either.Right(exception)
    }

    /** Check is provided id exists and create a new one if */
    private fun internalCreate(sensor: CreateSensor): CreateSensorResponse {
        if (sensor.isExists) throw Exception("${sensor.id} already exists")
        return internalCreate(sensor.toCreateSensorWithId(sensor.id ?: generator.generate))
    }

    /** Insert sensor to the database */
    private fun internalCreate(sensor: CreateSensorWithId): CreateSensorResponse {
        return database.sensor().insert().insertSensor(DatabaseSensor(sensor)).mapLeft { databaseInsertedSensor ->
            CreatedSensor(databaseInsertedSensor.sensor.toSensor())
        }
    }
}