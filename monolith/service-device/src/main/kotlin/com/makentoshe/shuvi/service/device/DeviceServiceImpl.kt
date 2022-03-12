package com.makentoshe.shuvi.service.device

import com.makentoshe.shuvi.common.flatMapLeft
import com.makentoshe.shuvi.entity.DeviceId
import com.makentoshe.shuvi.entity.service.NetworkDevice2
import com.makentoshe.shuvi.repository.GetDeviceRepository
import com.makentoshe.shuvi.response.service.NetworkGetDevicesResponse
import com.makentoshe.shuvi.service.ServiceCall
import com.makentoshe.shuvi.service.parameter
import io.ktor.http.HttpStatusCode

class DeviceServiceImpl(private val getDeviceRepository: GetDeviceRepository) : DeviceService {
    override suspend fun handle(call: ServiceCall) {
        call.parameter(deviceIdParameter).flatMapLeft { deviceIdString ->
            getDeviceRepository.getDevice(DeviceId(deviceIdString))
        }.bimap({ device ->
            NetworkGetDevicesResponse.Success(listOf(NetworkDevice2(device)))
        },{ exception ->
            NetworkGetDevicesResponse.Failure(exception)
        }).fold({ success ->
            call.respond(HttpStatusCode.OK, success)
        }, { failure ->
            call.respond(HttpStatusCode.InternalServerError, failure)
        })
    }
}