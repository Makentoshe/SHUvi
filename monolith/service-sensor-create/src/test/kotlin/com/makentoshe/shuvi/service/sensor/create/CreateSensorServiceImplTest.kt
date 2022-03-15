package com.makentoshe.shuvi.service.sensor.create

import com.makentoshe.shuvi.common.Either
import com.makentoshe.shuvi.entity.CreatedSensor
import com.makentoshe.shuvi.entity.Sensor
import com.makentoshe.shuvi.entity.SensorId
import com.makentoshe.shuvi.entity.service.NetworkSensor2
import com.makentoshe.shuvi.repository.sensor.CreateSensorRepository
import com.makentoshe.shuvi.entity.service.sensor.NetworkCreateSensor
import com.makentoshe.shuvi.entity.service.sensor.toCreateSensor
import com.makentoshe.shuvi.response.service.sensor.NetworkCreateSensorResponse
import com.makentoshe.shuvi.service.ServiceCall
import com.makentoshe.shuvi.service.receive
import io.ktor.http.HttpStatusCode
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

class CreateSensorServiceImplTest {

    private val mockRepository = mockk<CreateSensorRepository>()
    private val service = CreateSensorServiceImpl(mockRepository)

    @Test
    fun `test should return 500 on object receive error`() = runBlocking {
        val receiveException = Exception("Stub1") // prepare exception
        // receiving object should cause error
        val mockServiceCall = mockk<ServiceCall>(relaxed = true)
        coEvery { mockServiceCall.receive<NetworkCreateSensor>() } returns Either.Right(receiveException)
        // handle call
        service.handle(mockServiceCall)
        // check failure response was returned
        val failureResponse = NetworkCreateSensorResponse.Failure(receiveException)
        coVerify { mockServiceCall.respond(HttpStatusCode.InternalServerError, failureResponse) }
    }

    @Test
    fun `test should return 500 on repository error`() = runBlocking {
        val networkCreateSensor = NetworkCreateSensor(id = "sensor-id") // prepare additional object
        val repositoryException = Exception("Stub1") // prepare exception
        // receiving object should cause error
        val mockServiceCall = mockk<ServiceCall>(relaxed = true)
        coEvery { mockServiceCall.receive<NetworkCreateSensor>() } returns Either.Left(networkCreateSensor)
        every { mockRepository.create(networkCreateSensor.toCreateSensor()) } returns Either.Right(repositoryException)
        // handle call
        service.handle(mockServiceCall)
        // check failure response was returned
        val failureResponse = NetworkCreateSensorResponse.Failure(repositoryException)
        coVerify { mockServiceCall.respond(HttpStatusCode.InternalServerError, failureResponse) }
    }

    @Test
    fun `test should return 200 on repository success`() = runBlocking {
        // prepare additional objects
        val networkCreateSensor = NetworkCreateSensor(id = "sensor-id")
        val createdSensor = CreatedSensor(Sensor(SensorId("sensor-id"), ""))
        // receiving object should cause error
        val mockServiceCall = mockk<ServiceCall>(relaxed = true)
        coEvery { mockServiceCall.receive<NetworkCreateSensor>() } returns Either.Left(networkCreateSensor)
        every { mockRepository.create(networkCreateSensor.toCreateSensor()) } returns Either.Left(createdSensor)
        // handle call
        service.handle(mockServiceCall)
        // check failure response was returned
        val successResponse = NetworkCreateSensorResponse.Success(NetworkSensor2(createdSensor.sensor))
        coVerify { mockServiceCall.respond(HttpStatusCode.OK, successResponse) }
    }

}