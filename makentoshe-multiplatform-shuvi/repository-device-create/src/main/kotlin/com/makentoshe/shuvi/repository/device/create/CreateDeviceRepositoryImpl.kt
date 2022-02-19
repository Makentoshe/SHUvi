package com.makentoshe.shuvi.repository.device.create

import com.makentoshe.shuvi.common.Either
import com.makentoshe.shuvi.common.rightOrNull
import com.makentoshe.shuvi.database.Database
import com.makentoshe.shuvi.entity.CreatedDevice
import com.makentoshe.shuvi.entity.Device
import com.makentoshe.shuvi.entity.SensorId
import com.makentoshe.shuvi.entity.database.DatabaseDevice
import com.makentoshe.shuvi.entity.database.DatabaseGetSensors
import com.makentoshe.shuvi.entity.database.DatabaseSensor
import com.makentoshe.shuvi.entity.database.crossref.DatabaseDeviceSensorsCrossref
import com.makentoshe.shuvi.repository.CreateDeviceRepository
import com.makentoshe.shuvi.response.repository.CreatedDeviceResponse

class CreateDeviceRepositoryImpl(private val database: Database) : CreateDeviceRepository {

    /** Filter sensors (we need only sensors that wasn't created yet) */
    private val DatabaseGetSensors.missedSensors: List<SensorId>
        get() = requestedSensorIds.minus(foundSensors.map { it.id }.toSet())

    override fun createDevice(device: Device): CreatedDeviceResponse = try {
        internalCreateDevice(device)
    } catch (exception: Exception) {
        Either.Right(exception)
    }

    private fun internalCreateDevice(device: Device): Either<CreatedDevice, Exception> {
        // Check device record is already exists
        if (database.device().get().id(device.id).isLeft()) throw Exception("${device.id} already exists")

        // Get missed sensors and return an exception if issue caused
        val databaseSensors = database.sensor().get().ids(device.sensors.map { it.id }).onRight { throw it }
        // Insert missed sensors and crossrefs
        databaseSensors.onLeft(::insertMissedDatabaseSensors).onLeft { insertMissedDatabaseCrossref(device, it) }
        // Insert device
        return database.device().insert().insertDevice(DatabaseDevice(device)).mapLeft { insertedDatabaseDevice ->
            CreatedDevice(device, insertedDatabaseDevice.wasAcknowledged)
        }
    }

    private fun insertMissedDatabaseSensors(databaseGetSensors: DatabaseGetSensors) {
        databaseGetSensors.missedSensors.map { sensorId ->
            database.sensor().insert().insertSensor(DatabaseSensor(sensorId.string))
        }.mapNotNull { databaseInsertedSensorResponse ->
            databaseInsertedSensorResponse.rightOrNull()
        }.forEach { exception -> throw exception }
    }

    private fun insertMissedDatabaseCrossref(device: Device, databaseGetSensors: DatabaseGetSensors) {
        // Get missed crossrefs from missed sensors
        val missedCrossrefs = databaseGetSensors.missedSensors.map { sensorId ->
            DatabaseDeviceSensorsCrossref(device.id.string, sensorId.string)
        }
        // insert missed crossrefs and return an exception if it occurs
        database.crossref().device2Sensors().insert().insertCrossrefs(missedCrossrefs).onRight { throw it }
    }
}