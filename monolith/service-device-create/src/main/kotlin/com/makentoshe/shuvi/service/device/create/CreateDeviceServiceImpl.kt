package com.makentoshe.shuvi.service.device.create

import com.makentoshe.shuvi.common.andOtherLeft
import com.makentoshe.shuvi.common.flatMapLeft
import com.makentoshe.shuvi.common.flattenLeft
import com.makentoshe.shuvi.entity.service.device.NetworkCreateDevice
import com.makentoshe.shuvi.entity.service.device.toCreateDevice
import com.makentoshe.shuvi.entity.service.sensor.toCreateSensor
import com.makentoshe.shuvi.repository.CreateDeviceRepository
import com.makentoshe.shuvi.repository.crossref.CreateSensorDeviceCrossrefRepository
import com.makentoshe.shuvi.repository.sensor.CreateSensorRepository
import com.makentoshe.shuvi.response.service.NetworkCreatedDeviceResponse
import com.makentoshe.shuvi.service.ServiceCall
import com.makentoshe.shuvi.service.device.CreateDeviceService
import com.makentoshe.shuvi.service.receive
import io.ktor.http.HttpStatusCode

class CreateDeviceServiceImpl(
    private val createDeviceRepository: CreateDeviceRepository,
    private val createSensorRepository: CreateSensorRepository,
    private val crossrefRepository: CreateSensorDeviceCrossrefRepository,
) : CreateDeviceService {

    override suspend fun handle(call: ServiceCall) {
        val networkCreateDevice = call.receive<NetworkCreateDevice>()
        // create device
        val createdDevice = networkCreateDevice.flatMapLeft {
            createDeviceRepository.createDevice(it.toCreateDevice())
        }
        // create sensors
        val createdSensors = networkCreateDevice.flatMapLeft {
            it.sensors.map { createSensorRepository.create(it.toCreateSensor()) }.flattenLeft()
        }
        // bind device and sensors
        createdDevice.andOtherLeft(createdSensors) { createdDevice, createdSensors ->
            val sensorIds = createdSensors.map { createdSensor -> createdSensor.sensor.id }
            // return success if binding is successful
            crossrefRepository.createCrossrefs(createdDevice.device.id, sensorIds).mapLeft {
                NetworkCreatedDeviceResponse.Success(createdDevice, createdSensors)
            }
        }.flattenLeft().mapRight { exception -> // update repository responses and prepare for networking
            NetworkCreatedDeviceResponse.Failure(exception)
        }.fold({ success ->
            call.respond(HttpStatusCode.OK, success)
        }, { failure ->
            call.respond(HttpStatusCode.InternalServerError, failure)
        })
    }
}