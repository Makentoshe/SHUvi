package com.makentoshe.shuvi.service.device

import com.makentoshe.shuvi.entity.service.NetworkDevice
import com.makentoshe.shuvi.repository.DeviceRepository
import com.makentoshe.shuvi.response.service.NetworkGetDevicesResponse
import com.makentoshe.shuvi.service.DeviceService
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*

class DeviceServiceImpl(private val repository: DeviceRepository) : DeviceService {

    override suspend fun handle(call: ApplicationCall) {
        repository.getDevices().bimap({ devices ->
            NetworkGetDevicesResponse.Success(devices.map(::NetworkDevice))
        }, { exception ->
            NetworkGetDevicesResponse.Failure(exception)
        }).fold({ success ->
            call.respond(HttpStatusCode.OK, success)
        }, { failure ->
            call.respond(HttpStatusCode.InternalServerError, failure)
        })
    }
}