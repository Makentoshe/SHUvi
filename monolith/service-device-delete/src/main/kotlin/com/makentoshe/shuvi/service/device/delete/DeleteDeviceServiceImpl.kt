package com.makentoshe.shuvi.service.device.delete

import com.makentoshe.shuvi.entity.service.NetworkDeviceId
import com.makentoshe.shuvi.repository.DeleteDeviceRepository
import com.makentoshe.shuvi.response.service.NetworkDeleteDeviceResponse
import com.makentoshe.shuvi.service.device.DeleteDeviceService
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*

class DeleteDeviceServiceImpl(private val repository: DeleteDeviceRepository) : DeleteDeviceService {
    override suspend fun handle(call: ApplicationCall) {
        val receivedDeviceId = call.receive<NetworkDeviceId>()

        repository.deleteDevice(receivedDeviceId.toDeviceId()).bimap({ deletedDevice ->
            NetworkDeleteDeviceResponse.Success(deletedDevice)
        }, { exception ->
            NetworkDeleteDeviceResponse.Failure(receivedDeviceId, exception)
        }).fold({ success ->
            call.respond(HttpStatusCode.OK, success)
        }, { failure ->
            call.respond(HttpStatusCode.InternalServerError, failure)
        })
    }
}