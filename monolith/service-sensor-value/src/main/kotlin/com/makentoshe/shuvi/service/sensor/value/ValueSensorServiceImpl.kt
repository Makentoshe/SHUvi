package com.makentoshe.shuvi.service.sensor.value

import com.makentoshe.shuvi.common.andOtherLeft
import com.makentoshe.shuvi.common.flattenLeft
import com.makentoshe.shuvi.entity.SensorId
import com.makentoshe.shuvi.entity.sensor.value.CreateSensorValue
import com.makentoshe.shuvi.repository.sensor.value.SensorValueRepository
import com.makentoshe.shuvi.response.service.sensor.NetworkValueSensorResponse
import com.makentoshe.shuvi.service.ServiceCall
import com.makentoshe.shuvi.service.parameter
import com.makentoshe.shuvi.service.receiveInt
import com.makentoshe.shuvi.service.sensor.ValueSensorService
import io.ktor.http.HttpStatusCode

class ValueSensorServiceImpl(
    private val sensorValueRepository: SensorValueRepository,
) : ValueSensorService {

    override suspend fun handle(call: ServiceCall) {
        call.parameter(sensorIdParameter).andOtherLeft(call.receiveInt()) { sensorIdString, value ->
            sensorValueRepository.create(CreateSensorValue(valueId = null, SensorId(sensorIdString), value))
        }.flattenLeft().bimap({ createdSensorValue ->
            NetworkValueSensorResponse.Success(createdSensorValue)
        }, { exception ->
            NetworkValueSensorResponse.Failure(exception)
        }).fold({ success ->
            call.respond(HttpStatusCode.OK, success)
        }, { failure ->
            call.respond(HttpStatusCode.InternalServerError, failure)
        })
    }
}