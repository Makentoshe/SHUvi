package com.makentoshe.shuvi.service.device.create

import com.makentoshe.shuvi.entity.service.NetworkDevice
import com.makentoshe.shuvi.repository.CreateDeviceRepository
import com.makentoshe.shuvi.service.CreateDeviceService
import io.ktor.application.*
import io.ktor.request.*
import io.ktor.response.*

class CreateDeviceServiceImpl(private val repository: CreateDeviceRepository) : CreateDeviceService {
    override suspend fun handle(call: ApplicationCall) {
        val receivedDevice = call.receive<NetworkDevice>()
        repository.createDevice(receivedDevice.toDevice())
        call.respond(NetworkDevice("id"))
    }
}