package com.makentoshe.shuvi.service.sensor

import com.makentoshe.shuvi.common.flatMapLeft
import com.makentoshe.shuvi.entity.SensorId
import com.makentoshe.shuvi.entity.service.NetworkSensor2
import com.makentoshe.shuvi.repository.sensor.GetSensorRepository
import com.makentoshe.shuvi.response.service.sensor.NetworkGetSensorResponse
import com.makentoshe.shuvi.service.ServiceCall
import com.makentoshe.shuvi.service.parameter
import io.ktor.http.HttpStatusCode

class GetSensorServiceImpl(private val getSensorRepository: GetSensorRepository) : GetSensorService {

    override suspend fun handle(call: ServiceCall) {
        call.parameter(sensorIdParameter).flatMapLeft { sensorIdString ->
            getSensorRepository.id(SensorId(sensorIdString))
        }.bimap({ sensor ->
            NetworkGetSensorResponse.Success(NetworkSensor2(sensor))
        }, { exception ->
            NetworkGetSensorResponse.Failure(exception)
        }).fold({ success ->
            call.respond(HttpStatusCode.OK, success)
        }, { failure ->
            call.respond(HttpStatusCode.InternalServerError, failure)
        })
    }
}