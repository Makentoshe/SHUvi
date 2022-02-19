package com.makentoshe.shuvi.service.device.create

import com.makentoshe.shuvi.common.Either
import com.makentoshe.shuvi.common.flatMapLeft
import com.makentoshe.shuvi.entity.service.NetworkDevice
import com.makentoshe.shuvi.repository.CreateDeviceRepository
import com.makentoshe.shuvi.response.service.NetworkCreatedDeviceResponse
import com.makentoshe.shuvi.service.CreateDeviceService
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*

class CreateDeviceServiceImpl(private val repository: CreateDeviceRepository) : CreateDeviceService {
    override suspend fun handle(call: ApplicationCall) {
        receiveNetworkDevice(call).mapRight { exception ->
            NetworkCreatedDeviceResponse.Failure(exception)
        }.flatMapLeft { networkDevice ->
            repository.createDevice(networkDevice.toDevice()).bimap({ createdDevice ->
                NetworkCreatedDeviceResponse.Success(createdDevice)
            }, { exception ->
                NetworkCreatedDeviceResponse.Failure(exception)
            })
        }.fold({ success ->
            call.respond(HttpStatusCode.OK, success)
        }, { failure ->
            call.respond(HttpStatusCode.InternalServerError, failure)
        })
    }

    private suspend fun receiveNetworkDevice(call: ApplicationCall) = try {
        Either.Left(call.receive<NetworkDevice>())
    } catch (exception: Exception) {
        Either.Right(exception)
    }
}