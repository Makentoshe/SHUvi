package com.makentoshe.shuvi.service.device

import com.makentoshe.shuvi.service.DeviceService
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*

class DeviceServiceImpl : DeviceService {
    override suspend fun handle(call: ApplicationCall) {
        val deviceId = call.parameters[deviceIdParameter]
        if (deviceId == null) {
            call.respond(HttpStatusCode.BadRequest, "Sas")
        } else {
            call.respond(HttpStatusCode.OK, deviceId)
        }
    }
}