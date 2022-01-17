package com.makentoshe.shuvi.service.device

import com.makentoshe.shuvi.repository.DeviceRepository
import com.makentoshe.shuvi.service.DeviceService
import io.ktor.application.*
import io.ktor.response.*

class DeviceServiceImpl(private val repository: DeviceRepository) : DeviceService {

    override suspend fun handle(call: ApplicationCall) {
        val devices = repository.getDevices()
        call.respondText("{ test: \"json\", devices: [${devices.joinToString(", ")}] }")
    }
}