package com.makentoshe.shuvi.service.value.get

import com.makentoshe.shuvi.common.flattenLeft
import com.makentoshe.shuvi.common.leftOrElse
import com.makentoshe.shuvi.entity.SensorId
import com.makentoshe.shuvi.entity.service.value.NetworkValue
import com.makentoshe.shuvi.repository.sensor.value.ValueRepository
import com.makentoshe.shuvi.response.service.value.NetworkGetValueResponse
import com.makentoshe.shuvi.service.ServiceCall
import com.makentoshe.shuvi.service.query
import com.makentoshe.shuvi.service.value.GetValuesService
import io.ktor.http.HttpStatusCode

class GetValuesServiceImpl(private val valueRepository: ValueRepository) : GetValuesService {

    override suspend fun handle(call: ServiceCall) {
        val limit = call.query(limitQuery).mapLeft { limitString ->
            limitString.toIntOrNull()?.takeIf { it > 0 } ?: 50
        }.leftOrElse { 50 }

        // if sensor id presented
        call.query(sensorQuery).mapLeft { sensorString -> SensorId(sensorString) }.mapLeft { sensorId ->
            valueRepository.sensor(sensorId, limit)
        }.flattenLeft().onLeft { values ->
            val success = NetworkGetValueResponse.Success(values.map { value -> NetworkValue(value) })
            return call.respond(HttpStatusCode.OK, success)
        }

        valueRepository.all(limit).mapLeft { values -> values.map { value -> NetworkValue(value) } }.fold({ values ->
            call.respond(HttpStatusCode.OK, NetworkGetValueResponse.Success(values))
        }, { exception ->
            call.respond(HttpStatusCode.InternalServerError, NetworkGetValueResponse.Failure(exception))
        })
    }
}