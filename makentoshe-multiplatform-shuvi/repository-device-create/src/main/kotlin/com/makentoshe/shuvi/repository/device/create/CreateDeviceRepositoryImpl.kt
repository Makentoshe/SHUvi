package com.makentoshe.shuvi.repository.device.create

import com.makentoshe.shuvi.common.Either
import com.makentoshe.shuvi.common.database.DeviceIdGenerator
import com.makentoshe.shuvi.common.database.SensorIdGenerator
import com.makentoshe.shuvi.common.leftOrNull
import com.makentoshe.shuvi.common.rightOrNull
import com.makentoshe.shuvi.database.Database
import com.makentoshe.shuvi.entity.*
import com.makentoshe.shuvi.entity.database.DatabaseDevice
import com.makentoshe.shuvi.entity.database.DatabaseGetSensors
import com.makentoshe.shuvi.entity.database.DatabaseSensor
import com.makentoshe.shuvi.entity.database.crossref.DatabaseDeviceSensorsCrossref
import com.makentoshe.shuvi.entity.repository.device.create.CreateDeviceWithId
import com.makentoshe.shuvi.entity.repository.device.create.toCreateDeviceWithId
import com.makentoshe.shuvi.repository.CreateDeviceRepository
import com.makentoshe.shuvi.response.repository.CreatedDeviceResponse
import org.apache.commons.lang3.RandomStringUtils

class CreateDeviceRepositoryImpl(
    private val database: Database,
    private val generator: DeviceIdGenerator,
) : CreateDeviceRepository {

    /** Return true if device exists and false otherwise */
    private val CreateDevice.isExists: Boolean get() = id?.let { database.device().get().id(it).isLeft() } == true

//    /** Filter sensors (we need only sensors that wasn't created yet) */
//    private val DatabaseGetSensors.missedSensors: List<SensorId>
//        get() = requestedSensorIds.minus(foundSensors.map { it.id }.toSet())

    override fun createDevice(device: CreateDevice): CreatedDeviceResponse = try {
        internalCreateDevice(device)
    } catch (exception: Exception) {
        Either.Right(exception)
    }

    private fun internalCreateDevice(device: CreateDevice): Either<CreatedDevice, Exception> {
        if (device.isExists) throw Exception("${device.id} already exists")
        return internalCreateDevice(device.toCreateDeviceWithId(device.id ?: generator.generate))
    }

    private fun internalCreateDevice(device: CreateDeviceWithId): Either<CreatedDevice, Exception> {
        // Get missed sensors and return an exception if issue caused
//        val databaseSensors = database.sensor().get().ids(device.sensors.map { it.id }).onRight { throw it }
        // Insert missed sensors and crossrefs
//        databaseSensors.onLeft(::insertDatabaseSensors).onLeft { insertMissedDatabaseCrossref(device.id, it) }
        // Insert device
        return database.device().insert().insertDevice(DatabaseDevice(device)).mapLeft { insertedDatabaseDevice ->
            CreatedDevice(device, insertedDatabaseDevice.wasAcknowledged)
        }
    }

//    private fun insertDatabaseSensors(databaseGetSensors: DatabaseGetSensors) {
//        databaseGetSensors.missedSensors.filter { sensorId -> // found all missed sensors
//            database.sensor().get().id(sensorId).isRight()
//        }.map { sensorId -> // insert missed sensors
//            database.sensor().insert().insertSensor(DatabaseSensor(sensorId.string, "TODO Createdevicerepo"))
//        }.mapNotNull { databaseInsertedSensorResponse -> // find all insertion exceptions
//            databaseInsertedSensorResponse.rightOrNull()
//        }.forEach { exception -> throw exception } // throw 'em
//    }

//    private fun insertMissedDatabaseCrossref(deviceId: DeviceId, databaseGetSensors: DatabaseGetSensors) {
//        databaseGetSensors.requestedSensorIds.map { sensorId -> // create crossref
//            DatabaseDeviceSensorsCrossref(deviceId.string, sensorId.string)
//        }.filterNot { crossref -> // filter all not existing crossrefs
//            database.crossref().device2Sensors().exists().crossref(crossref).leftOrNull()?.isExists ?: true
//        }.let { missedCrossrefs ->
//            if (missedCrossrefs.isEmpty()) return@let // ignore empty crossrefs list
//            database.crossref().device2Sensors().insert().insertCrossrefs(missedCrossrefs).onRight { throw it }
//        }
//    }
}