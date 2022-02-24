package com.makentoshe.shuvi.service.device

import com.makentoshe.shuvi.entity.DeviceId
import com.makentoshe.shuvi.entity.service.NetworkDevice2
import com.makentoshe.shuvi.repository.GetDeviceRepository
import com.makentoshe.shuvi.service.DeviceService
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*

class DeviceServiceImpl(private val getDeviceRepository: GetDeviceRepository) : DeviceService {
    override suspend fun handle(call: ApplicationCall) {
        val deviceId = call.parameters[deviceIdParameter]
            ?: return call.respond(HttpStatusCode.BadRequest, "Sas")

        getDeviceRepository.getDevice(DeviceId(deviceId)).fold({ device ->
            call.respond(HttpStatusCode.OK, NetworkDevice2(device))
        },{ exception ->
            call.respond(HttpStatusCode.OK, exception.toString())
        })
    }
}