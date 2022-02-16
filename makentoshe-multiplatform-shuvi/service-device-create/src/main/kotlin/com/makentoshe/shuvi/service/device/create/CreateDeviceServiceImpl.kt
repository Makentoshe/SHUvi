package com.makentoshe.shuvi.service.device.create

import com.makentoshe.shuvi.response.service.NetworkCreatedDeviceResponse
import com.makentoshe.shuvi.entity.service.NetworkDevice
import com.makentoshe.shuvi.repository.CreateDeviceRepository
import com.makentoshe.shuvi.service.CreateDeviceService
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*

class CreateDeviceServiceImpl(private val repository: CreateDeviceRepository) : CreateDeviceService {
    override suspend fun handle(call: ApplicationCall) {
        val receivedDevice = call.receive<NetworkDevice>()

        repository.createDevice(receivedDevice.toDevice()).bimap({ createdDevice ->
            NetworkCreatedDeviceResponse.Success(createdDevice)
        }, { exception ->
            NetworkCreatedDeviceResponse.Failure(receivedDevice, exception)
        }).fold({ success ->
            call.respond(HttpStatusCode.OK, success)
        }, { failure ->
            call.respond(HttpStatusCode.InternalServerError, failure)
        })
    }
}