package com.makentoshe.shuvi.service.device.create

import com.makentoshe.shuvi.common.Either
import com.makentoshe.shuvi.common.andOtherLeft
import com.makentoshe.shuvi.common.flatMapLeft
import com.makentoshe.shuvi.common.flattenLeft
import com.makentoshe.shuvi.entity.service.device.NetworkCreateDevice
import com.makentoshe.shuvi.entity.service.device.toCreateDevice
import com.makentoshe.shuvi.entity.service.sensor.toCreateSensor
import com.makentoshe.shuvi.repository.CreateDeviceRepository
import com.makentoshe.shuvi.repository.sensor.CreateSensorRepository
import com.makentoshe.shuvi.response.service.NetworkCreatedDeviceResponse
import com.makentoshe.shuvi.service.CreateDeviceService
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*

class CreateDeviceServiceImpl(
    private val createDeviceRepository: CreateDeviceRepository,
    private val createSensorRepository: CreateSensorRepository,
) : CreateDeviceService {

    override suspend fun handle(call: ApplicationCall) {
        val networkCreateDevice = receiveNetworkDevice(call)

        val createdDevice = networkCreateDevice.flatMapLeft {
            createDeviceRepository.createDevice(it.toCreateDevice())
        }

        val createdSensors = networkCreateDevice.flatMapLeft {
            it.sensors.map { createSensorRepository.create(it.toCreateSensor()) }.flattenLeft()
        }

        createdDevice.andOtherLeft(createdSensors) { createdDevice, createdSensors ->
            NetworkCreatedDeviceResponse.Success(createdDevice, createdSensors)
        }.mapRight { exception ->
            NetworkCreatedDeviceResponse.Failure(exception)
        }.fold({ success ->
            call.respond(HttpStatusCode.OK, success)
        }, { failure ->
            call.respond(HttpStatusCode.InternalServerError, failure)
        })
    }

    private suspend fun receiveNetworkDevice(call: ApplicationCall) = try {
        Either.Left(call.receive<NetworkCreateDevice>())
    } catch (exception: Exception) {
        Either.Right(exception)
    }
}