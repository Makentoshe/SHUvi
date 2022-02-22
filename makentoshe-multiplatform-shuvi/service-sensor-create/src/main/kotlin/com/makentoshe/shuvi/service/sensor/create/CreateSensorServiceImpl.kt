package com.makentoshe.shuvi.service.sensor.create

import com.makentoshe.shuvi.common.Either
import com.makentoshe.shuvi.common.flatMapLeft
import com.makentoshe.shuvi.entity.service.NetworkSensor
import com.makentoshe.shuvi.response.service.sensor.NetworkCreateSensor
import com.makentoshe.shuvi.repository.sensor.CreateSensorRepository
import com.makentoshe.shuvi.response.service.sensor.NetworkCreateSensorResponse
import com.makentoshe.shuvi.service.sensor.CreateSensorService
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*

class CreateSensorServiceImpl(private val createSensorRepository: CreateSensorRepository) : CreateSensorService {
    override suspend fun handle(call: ApplicationCall) {
        receiveNetworkCreateSensor(call).flatMapLeft { networkCreateSensor ->
            createSensorRepository.create(networkCreateSensor.toCreateSensor())
        }.bimap({ createdSensor ->
            NetworkCreateSensorResponse.Success(NetworkSensor(createdSensor.sensor))
        }, { exception ->
            NetworkCreateSensorResponse.Failure(exception)
        }).fold({ success ->
            call.respond(HttpStatusCode.OK, success)
        }, { failure ->
            call.respond(HttpStatusCode.InternalServerError, failure)
        })
    }

    private suspend fun receiveNetworkCreateSensor(call: ApplicationCall) = try {
        Either.Left(call.receive<NetworkCreateSensor>())
    } catch (exception: Exception) {
        Either.Right(exception)
    }
}