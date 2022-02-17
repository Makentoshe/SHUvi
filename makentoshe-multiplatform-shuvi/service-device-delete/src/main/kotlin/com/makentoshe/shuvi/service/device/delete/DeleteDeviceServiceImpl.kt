package com.makentoshe.shuvi.service.device.delete

import com.makentoshe.shuvi.entity.service.NetworkDeviceId
import com.makentoshe.shuvi.repository.DeleteDeviceRepository
import com.makentoshe.shuvi.service.DeleteDeviceService
import io.ktor.application.*
import io.ktor.request.*
import io.ktor.response.*

class DeleteDeviceServiceImpl(private val repository: DeleteDeviceRepository) : DeleteDeviceService {
    override suspend fun handle(call: ApplicationCall) {
        val receivedDeviceId = call.receive<NetworkDeviceId>()
        val repositoryResponse = repository.deleteDevice(receivedDeviceId.toDeviceId())
        println(repositoryResponse)
        call.respondText { "Device with $receivedDeviceId will be deleted" }
    }
}