package com.makentoshe.shuvi.service.device.create

import com.makentoshe.shuvi.entity.service.NetworkDevice
import com.makentoshe.shuvi.service.CreateDeviceService
import io.ktor.application.*
import io.ktor.request.*
import io.ktor.response.*

class CreateDeviceServiceImpl : CreateDeviceService {
    override suspend fun handle(call: ApplicationCall) {
        println(call.receive<NetworkDevice>())
        call.respond(NetworkDevice("id"))
    }
}