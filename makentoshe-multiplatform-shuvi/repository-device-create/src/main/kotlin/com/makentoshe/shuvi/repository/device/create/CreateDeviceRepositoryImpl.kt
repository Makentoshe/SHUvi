package com.makentoshe.shuvi.repository.device.create

import com.makentoshe.shuvi.common.Either
import com.makentoshe.shuvi.common.database.DeviceIdGenerator
import com.makentoshe.shuvi.database.Database
import com.makentoshe.shuvi.entity.*
import com.makentoshe.shuvi.entity.database.DatabaseDevice
import com.makentoshe.shuvi.entity.device.CreateDevice
import com.makentoshe.shuvi.entity.device.CreatedDevice
import com.makentoshe.shuvi.repository.CreateDeviceRepository
import com.makentoshe.shuvi.response.repository.CreatedDeviceResponse

class CreateDeviceRepositoryImpl(
    private val database: Database,
    private val generator: DeviceIdGenerator,
) : CreateDeviceRepository {

    /** Return true if device exists and false otherwise */
    private val CreateDevice.isExists: Boolean get() = id?.let { database.device().get().id(it).isLeft() } == true

    override fun createDevice(device: CreateDevice): CreatedDeviceResponse = try {
        internalCreateDevice(device)
    } catch (exception: Exception) {
        Either.Right(exception)
    }

    private fun internalCreateDevice(device: CreateDevice): Either<CreatedDevice, Exception> {
        if (device.isExists) throw Exception("${device.id} already exists")
        return internalCreateDevice(device.id ?: generator.generate, device)
    }

    private fun internalCreateDevice(deviceId: DeviceId, createDevice: CreateDevice): Either<CreatedDevice, Exception> {
        val databaseDevice = DatabaseDevice(deviceId, createDevice)
        return database.device().insert().insertDevice(databaseDevice).mapLeft { insertedDatabaseDevice ->
            CreatedDevice(createDevice, insertedDatabaseDevice.databaseDevice.toDevice())
        }
    }

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