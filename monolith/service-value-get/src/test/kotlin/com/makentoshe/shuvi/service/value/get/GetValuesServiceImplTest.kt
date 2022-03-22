package com.makentoshe.shuvi.service.value.get

import com.makentoshe.shuvi.common.Either
import com.makentoshe.shuvi.entity.SensorId
import com.makentoshe.shuvi.entity.sensor.value.SensorValueId
import com.makentoshe.shuvi.entity.sensor.value.Value
import com.makentoshe.shuvi.entity.service.value.NetworkValue
import com.makentoshe.shuvi.repository.sensor.value.ValueRepository
import com.makentoshe.shuvi.response.service.value.NetworkGetValueResponse
import com.makentoshe.shuvi.service.ServiceCall
import com.makentoshe.shuvi.service.query
import io.ktor.http.HttpStatusCode
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

class GetValuesServiceImplTest {

    private val mockRepository = mockk<ValueRepository>()
    private val service = GetValuesServiceImpl(mockRepository)

    @Test
    fun `test should response with successful limit`() = runBlocking {
        val limitQuery = "25"
        val values = listOf(Value(SensorValueId("sas"), SensorId("asa"), 321, "timestamp"))

        val mockServiceCall = mockk<ServiceCall>(relaxed = true)
        every { mockServiceCall.queries[service.limitQuery] } returns limitQuery
        every { mockServiceCall.queries[service.sensorQuery] } returns null

        every { mockRepository.all(limitQuery.toInt()) } returns Either.Left(values)

        service.handle(mockServiceCall)

        val success = NetworkGetValueResponse.Success(values.map { NetworkValue(it) })
        coVerify { mockServiceCall.respond(HttpStatusCode.OK, success) }
    }

    @Test
    fun `test should response with successful query`() = runBlocking {
        val sensorQuery = "sensorid"
        val values = listOf(Value(SensorValueId("sas"), SensorId("asa"), 321, "timestamp"))

        val mockServiceCall = mockk<ServiceCall>(relaxed = true)
        every { mockServiceCall.queries[service.limitQuery] } returns null
        every { mockServiceCall.queries[service.sensorQuery] } returns sensorQuery

        every { mockRepository.sensor(SensorId(sensorQuery), 50) } returns Either.Left(values)

        service.handle(mockServiceCall)

        val success = NetworkGetValueResponse.Success(values.map { NetworkValue(it) })
        coVerify { mockServiceCall.respond(HttpStatusCode.OK, success) }
    }

    @Test
    fun `test should response with failure`() = runBlocking {
        val exception = Exception("Repository exception")
        val limitQuery = "25"

        val mockServiceCall = mockk<ServiceCall>(relaxed = true)
        every { mockServiceCall.queries[service.limitQuery] } returns limitQuery
        every { mockServiceCall.queries[service.sensorQuery] } returns null

        every { mockRepository.all(limitQuery.toInt()) } returns Either.Right(exception)

        service.handle(mockServiceCall)

        val failure = NetworkGetValueResponse.Failure(exception)
        coVerify { mockServiceCall.respond(HttpStatusCode.InternalServerError, failure) }
    }

    @Test
    fun `test should check default limit query`() = runBlocking {
        val values = listOf(Value(SensorValueId("sas"), SensorId("asa"), 321, "timestamp"))

        val mockServiceCall = mockk<ServiceCall>(relaxed = true)
        every { mockServiceCall.queries[service.limitQuery] } returns null
        every { mockServiceCall.queries[service.sensorQuery] } returns null

        every { mockRepository.all(50) } returns Either.Left(values)

        service.handle(mockServiceCall)
    }

    @Test
    fun `test should check negative limit query`() = runBlocking {
        val values = listOf(Value(SensorValueId("sas"), SensorId("asa"), 321, "timestamp"))

        val mockServiceCall = mockk<ServiceCall>(relaxed = true)
        every { mockServiceCall.queries[service.limitQuery] } returns "-13"
        every { mockServiceCall.queries[service.sensorQuery] } returns null

        every { mockRepository.all(50) } returns Either.Left(values)

        service.handle(mockServiceCall)
    }

    @Test
    fun `test should check invalid limit query`() = runBlocking {
        val values = listOf(Value(SensorValueId("sas"), SensorId("asa"), 321, "timestamp"))

        val mockServiceCall = mockk<ServiceCall>(relaxed = true)
        every { mockServiceCall.queries[service.limitQuery] } returns "sasasaanuspsa"
        every { mockServiceCall.queries[service.sensorQuery] } returns null

        every { mockRepository.all(50) } returns Either.Left(values)

        service.handle(mockServiceCall)
    }
}