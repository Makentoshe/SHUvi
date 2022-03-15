package com.makentoshe.shuvi.service.sensor.create

import com.makentoshe.shuvi.common.flatMapLeft
import com.makentoshe.shuvi.entity.service.NetworkSensor2
import com.makentoshe.shuvi.entity.service.sensor.NetworkCreateSensor
import com.makentoshe.shuvi.entity.service.sensor.toCreateSensor
import com.makentoshe.shuvi.repository.sensor.CreateSensorRepository
import com.makentoshe.shuvi.response.service.sensor.NetworkCreateSensorResponse
import com.makentoshe.shuvi.service.ServiceCall
import com.makentoshe.shuvi.service.receive
import com.makentoshe.shuvi.service.sensor.CreateSensorService
import io.ktor.http.HttpStatusCode

class CreateSensorServiceImpl(private val createSensorRepository: CreateSensorRepository) : CreateSensorService {

    override suspend fun handle(call: ServiceCall) {
        call.receive<NetworkCreateSensor>().flatMapLeft { networkCreateSensor ->
            createSensorRepository.create(networkCreateSensor.toCreateSensor())
        }.bimap({ createdSensor ->
            NetworkCreateSensorResponse.Success(NetworkSensor2(createdSensor.sensor))
        }, { exception ->
            NetworkCreateSensorResponse.Failure(exception)
        }).fold({ success ->
            call.respond(HttpStatusCode.OK, success)
        }, { failure ->
            call.respond(HttpStatusCode.InternalServerError, failure)
        })
    }
}