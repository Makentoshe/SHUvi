package com.makentoshe.shuvi.service.sensor.value

import com.makentoshe.shuvi.common.flatMapLeft
import com.makentoshe.shuvi.entity.SensorId
import com.makentoshe.shuvi.response.service.sensor.NetworkValueSensorResponse
import com.makentoshe.shuvi.service.ServiceCall
import com.makentoshe.shuvi.service.parameter
import com.makentoshe.shuvi.service.receiveInt
import com.makentoshe.shuvi.service.sensor.ValueSensorService
import com.makentoshe.shuvi.service.timestamp
import io.ktor.http.HttpStatusCode

class ValueSensorServiceImpl : ValueSensorService {

    override suspend fun handle(call: ServiceCall) {
        call.parameter(sensorIdParameter).flatMapLeft { sensorIdString ->
            call.receiveInt().mapLeft { value -> SensorId(sensorIdString) to value }
        }.bimap({ batch ->
            NetworkValueSensorResponse.Success(batch.first.string, batch.second, timestamp)
        }, { exception ->
            NetworkValueSensorResponse.Failure(exception)
        }).fold({ success ->
            call.respond(HttpStatusCode.OK, success)
        }, { failure ->
            call.respond(HttpStatusCode.InternalServerError, failure)
        })
    }
}