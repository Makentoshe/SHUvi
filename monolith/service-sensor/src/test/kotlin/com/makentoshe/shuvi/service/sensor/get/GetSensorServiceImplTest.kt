package com.makentoshe.shuvi.service.sensor.get

import com.makentoshe.shuvi.common.Either
import com.makentoshe.shuvi.entity.Sensor
import com.makentoshe.shuvi.entity.SensorId
import com.makentoshe.shuvi.entity.service.NetworkSensor2
import com.makentoshe.shuvi.repository.sensor.GetSensorRepository
import com.makentoshe.shuvi.response.service.sensor.NetworkGetSensorResponse
import com.makentoshe.shuvi.service.ServiceCall
import com.makentoshe.shuvi.service.sensor.GetSensorServiceImpl
import io.ktor.http.HttpStatusCode
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

class GetSensorServiceImplTest {

    private val mockRepository = mockk<GetSensorRepository>()
    private val service = GetSensorServiceImpl(mockRepository)

    @Test
    fun `test should return 500 on parameter receive error`() = runBlocking {
        val parameterException = Exception("${service.sensorIdParameter} parameter issue") // prepare hardcoded exception
        // receiving parameter should cause error
        val mockServiceCall = mockk<ServiceCall>(relaxed = true)
        every { mockServiceCall.parameters[service.sensorIdParameter] } returns null
        // handle call
        service.handle(mockServiceCall)
        // check failure response was returned
        val failureResponse = NetworkGetSensorResponse.Failure(parameterException)
        coVerify { mockServiceCall.respond(HttpStatusCode.InternalServerError, failureResponse) }
    }

    @Test
    fun `test should return 500 on repository error`() = runBlocking {
        val sensorIdParameter = "sensor-id-sas"
        val repositoryException = Exception("Stub1") // prepare exception
        // receiving object should cause error
        val mockServiceCall = mockk<ServiceCall>(relaxed = true)
        every { mockServiceCall.parameters[service.sensorIdParameter] } returns sensorIdParameter
        every { mockRepository.id(SensorId(sensorIdParameter)) } returns Either.Right(repositoryException)
        // handle call
        service.handle(mockServiceCall)
        // check failure response was returned
        val failureResponse = NetworkGetSensorResponse.Failure(repositoryException)
        coVerify { mockServiceCall.respond(HttpStatusCode.InternalServerError, failureResponse) }
    }

    @Test
    fun `test should return 200 on repository success`() = runBlocking {
        // prepare additional objects
        val sensorIdParameter = "sensor-id-sas"
        val sensor = Sensor(SensorId(sensorIdParameter), "")
        // receiving object should cause error
        val mockServiceCall = mockk<ServiceCall>(relaxed = true)
        every { mockServiceCall.parameters[service.sensorIdParameter] } returns sensorIdParameter
        every { mockRepository.id(SensorId(sensorIdParameter)) } returns Either.Left(sensor)
        // handle call
        service.handle(mockServiceCall)
        // check failure response was returned
        val successResponse = NetworkGetSensorResponse.Success(NetworkSensor2(sensor))
        coVerify { mockServiceCall.respond(HttpStatusCode.OK, successResponse) }
    }
}