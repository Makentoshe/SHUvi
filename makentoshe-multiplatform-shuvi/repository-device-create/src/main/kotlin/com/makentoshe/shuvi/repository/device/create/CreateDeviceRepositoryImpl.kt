package com.makentoshe.shuvi.repository.device.create

import com.makentoshe.shuvi.common.Either
import com.makentoshe.shuvi.common.leftOrNull
import com.makentoshe.shuvi.common.rightOrNull
import com.makentoshe.shuvi.database.Database
import com.makentoshe.shuvi.entity.*
import com.makentoshe.shuvi.entity.database.DatabaseDevice
import com.makentoshe.shuvi.entity.database.DatabaseGetSensors
import com.makentoshe.shuvi.entity.database.DatabaseSensor
import com.makentoshe.shuvi.entity.database.crossref.DatabaseDeviceSensorsCrossref
import com.makentoshe.shuvi.repository.CreateDeviceRepository
import com.makentoshe.shuvi.response.repository.CreatedDeviceResponse
import org.apache.commons.lang3.RandomStringUtils

private typealias CreateDeviceWithId = Device

private fun CreateDevice.toCreateDeviceWithId(deviceId: DeviceId): CreateDeviceWithId {
    return Device(deviceId, title, sensors)
}

class CreateDeviceRepositoryImpl(private val database: Database) : CreateDeviceRepository {

    /** Filter sensors (we need only sensors that wasn't created yet) */
    private val DatabaseGetSensors.missedSensors: List<SensorId>
        get() = requestedSensorIds.minus(foundSensors.map { it.id }.toSet())

    // TODO extract this feature to another class
    /** Generates a 8byte device id. It will retry recursively till unique id will be created */
    @Suppress("RecursivePropertyAccessor")
    private val generateDeviceId: DeviceId
        get() {
            val deviceId = DeviceId(RandomStringUtils.randomAlphanumeric(8))
            return if (database.device().get().id(deviceId).isRight()) deviceId else generateDeviceId
        }

    override fun createDevice(device: CreateDevice): CreatedDeviceResponse = try {
        internalCreateDevice(device)
    } catch (exception: Exception) {
        Either.Right(exception)
    }

    private fun internalCreateDevice(device: CreateDevice): Either<CreatedDevice, Exception> {
        var deviceId = device.id // get current device id
        if (deviceId != null) { // if device id was specified
            // Check device record is already exists
            if (database.device().get().id(deviceId).isLeft()) throw Exception("${device.id} already exists")
        } else {
            // generate new 8byte device id
            deviceId = generateDeviceId
        }
        // continue to create device
        return internalCreateDevice(device.toCreateDeviceWithId(deviceId))
    }

    private fun internalCreateDevice(device: CreateDeviceWithId): Either<CreatedDevice, Exception> {
        // Get missed sensors and return an exception if issue caused
        val databaseSensors = database.sensor().get().ids(device.sensors.map { it.id }).onRight { throw it }
        // Insert missed sensors and crossrefs
        databaseSensors.onLeft(::insertDatabaseSensors).onLeft { insertMissedDatabaseCrossref(device.id, it) }
        // Insert device
        return database.device().insert().insertDevice(DatabaseDevice(device)).mapLeft { insertedDatabaseDevice ->
            CreatedDevice(device, insertedDatabaseDevice.wasAcknowledged)
        }
    }

    private fun insertDatabaseSensors(databaseGetSensors: DatabaseGetSensors) {
        databaseGetSensors.missedSensors.filter { sensorId -> // found all missed sensors
            database.sensor().get().id(sensorId).isRight()
        }.map { sensorId -> // insert missed sensors
            database.sensor().insert().insertSensor(DatabaseSensor(sensorId.string))
        }.mapNotNull { databaseInsertedSensorResponse -> // find all insertion exceptions
            databaseInsertedSensorResponse.rightOrNull()
        }.forEach { exception -> throw exception } // throw 'em
    }

    private fun insertMissedDatabaseCrossref(deviceId: DeviceId, databaseGetSensors: DatabaseGetSensors) {
        databaseGetSensors.requestedSensorIds.map { sensorId -> // create crossref
            DatabaseDeviceSensorsCrossref(deviceId.string, sensorId.string)
        }.filterNot { crossref -> // filter all not existing crossrefs
            database.crossref().device2Sensors().exists().crossref(crossref).leftOrNull()?.isExists ?: true
        }.let { missedCrossrefs ->
            if (missedCrossrefs.isEmpty()) return@let // ignore empty crossrefs list
            database.crossref().device2Sensors().insert().insertCrossrefs(missedCrossrefs).onRight { throw it }
        }
    }
}