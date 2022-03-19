package com.makentoshe.shuvi.service.value.get

import com.makentoshe.shuvi.common.leftOrElse
import com.makentoshe.shuvi.entity.service.value.NetworkValue
import com.makentoshe.shuvi.repository.sensor.value.ValueRepository
import com.makentoshe.shuvi.response.service.value.NetworkGetValueResponse
import com.makentoshe.shuvi.service.ServiceCall
import com.makentoshe.shuvi.service.query
import com.makentoshe.shuvi.service.value.GetValueService
import io.ktor.http.HttpStatusCode

class GetValueServiceImpl(private val valueRepository: ValueRepository) : GetValueService {

    override suspend fun handle(call: ServiceCall) {
        val limit = call.query(limitQuery).mapLeft { limitString ->
            limitString.toIntOrNull()?.takeIf { it > 0 } ?: 50
        }.leftOrElse { 50 }

        valueRepository.all(limit).mapLeft { values -> values.map { value -> NetworkValue(value) } }.fold({ values ->
            call.respond(HttpStatusCode.OK, NetworkGetValueResponse.Success(values))
        }, { exception ->
            call.respond(HttpStatusCode.InternalServerError, NetworkGetValueResponse.Failure(exception))
        })
    }
}