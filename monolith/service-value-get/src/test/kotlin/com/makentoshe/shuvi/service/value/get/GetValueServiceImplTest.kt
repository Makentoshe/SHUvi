package com.makentoshe.shuvi.service.value.get

import com.makentoshe.shuvi.common.Either
import com.makentoshe.shuvi.entity.SensorId
import com.makentoshe.shuvi.entity.sensor.value.SensorValueId
import com.makentoshe.shuvi.entity.sensor.value.Value
import com.makentoshe.shuvi.entity.service.value.NetworkValue
import com.makentoshe.shuvi.repository.sensor.value.ValueRepository
import com.makentoshe.shuvi.response.service.value.NetworkGetValueResponse
import com.makentoshe.shuvi.service.ServiceCall
import io.ktor.http.HttpStatusCode
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

class GetValueServiceImplTest {

    private val mockRepository = mockk<ValueRepository>()
    private val service = GetValueServiceImpl(mockRepository)

    @Test
    fun `test should response with successful`() = runBlocking {
        val limitQuery = "25"
        val values = listOf(Value(SensorValueId("sas"), SensorId("asa"), 321, "timestamp"))

        val mockServiceCall = mockk<ServiceCall>(relaxed = true)
        every { mockServiceCall.queries[any()] } returns limitQuery

        every { mockRepository.all(limitQuery.toInt()) } returns Either.Left(values)

        service.handle(mockServiceCall)

        val success = NetworkGetValueResponse.Success(values.map { NetworkValue(it) })
        coVerify { mockServiceCall.respond(HttpStatusCode.OK, success) }
    }

    @Test
    fun `test should response with failure`() = runBlocking {
        val exception = Exception("Repository exception")
        val limitQuery = "25"

        val mockServiceCall = mockk<ServiceCall>(relaxed = true)
        every { mockServiceCall.queries[any()] } returns limitQuery

        every { mockRepository.all(limitQuery.toInt()) } returns Either.Right(exception)

        service.handle(mockServiceCall)

        val failure = NetworkGetValueResponse.Failure(exception)
        coVerify { mockServiceCall.respond(HttpStatusCode.InternalServerError, failure) }
    }

    @Test
    fun `test should check default limit query`() = runBlocking {
        val values = listOf(Value(SensorValueId("sas"), SensorId("asa"), 321, "timestamp"))

        val mockServiceCall = mockk<ServiceCall>(relaxed = true)
        every { mockServiceCall.queries[any()] } returns null

        every { mockRepository.all(50) } returns Either.Left(values)

        service.handle(mockServiceCall)
    }

    @Test
    fun `test should check negative limit query`() = runBlocking {
        val values = listOf(Value(SensorValueId("sas"), SensorId("asa"), 321, "timestamp"))

        val mockServiceCall = mockk<ServiceCall>(relaxed = true)
        every { mockServiceCall.queries[any()] } returns "-13"

        every { mockRepository.all(50) } returns Either.Left(values)

        service.handle(mockServiceCall)
    }

    @Test
    fun `test should check invalid limit query`() = runBlocking {
        val values = listOf(Value(SensorValueId("sas"), SensorId("asa"), 321, "timestamp"))

        val mockServiceCall = mockk<ServiceCall>(relaxed = true)
        every { mockServiceCall.queries[any()] } returns "asasasasd"

        every { mockRepository.all(50) } returns Either.Left(values)

        service.handle(mockServiceCall)
    }
}