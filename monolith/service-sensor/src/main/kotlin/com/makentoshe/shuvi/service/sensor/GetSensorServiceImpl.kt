package com.makentoshe.shuvi.service.sensor

import com.makentoshe.shuvi.entity.SensorId
import com.makentoshe.shuvi.entity.service.NetworkSensor
import com.makentoshe.shuvi.entity.service.NetworkSensor2
import com.makentoshe.shuvi.repository.sensor.GetSensorRepository
import com.makentoshe.shuvi.response.service.sensor.NetworkGetSensorResponse
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*

class GetSensorServiceImpl(private val getSensorRepository: GetSensorRepository) : GetSensorService {
    override suspend fun handle(call: ApplicationCall) {
        val sensorId = call.parameters[sensorIdParameter]
            ?: return call.respond(HttpStatusCode.BadRequest, "TODO handle sensor id parameter issue.")

        getSensorRepository.id(SensorId(sensorId)).bimap({ sensor ->
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