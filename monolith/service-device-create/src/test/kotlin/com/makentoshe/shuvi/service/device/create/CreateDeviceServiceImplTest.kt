package com.makentoshe.shuvi.service.device.create

import com.makentoshe.shuvi.common.Either
import com.makentoshe.shuvi.entity.CreatedSensor
import com.makentoshe.shuvi.entity.DeviceId
import com.makentoshe.shuvi.entity.Sensor
import com.makentoshe.shuvi.entity.SensorId
import com.makentoshe.shuvi.entity.device.CreateDevice
import com.makentoshe.shuvi.entity.device.CreatedDevice
import com.makentoshe.shuvi.entity.device.Device
import com.makentoshe.shuvi.entity.service.device.NetworkCreateDevice
import com.makentoshe.shuvi.entity.service.sensor.NetworkCreateSensor
import com.makentoshe.shuvi.repository.CreateDeviceRepository
import com.makentoshe.shuvi.repository.crossref.CreateSensorDeviceCrossrefRepository
import com.makentoshe.shuvi.repository.sensor.CreateSensorRepository
import com.makentoshe.shuvi.response.service.NetworkCreatedDeviceResponse
import com.makentoshe.shuvi.service.ServiceCall
import com.makentoshe.shuvi.service.receive
import io.ktor.http.HttpStatusCode
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

class CreateDeviceServiceImplTest {

    private val mockCreateDeviceRepository = mockk<CreateDeviceRepository>()
    private val mockCreateSensorRepository = mockk<CreateSensorRepository>()
    private val mockCreateCrossrefRepository = mockk<CreateSensorDeviceCrossrefRepository>()
    private val service = CreateDeviceServiceImpl(
        mockCreateDeviceRepository, mockCreateSensorRepository, mockCreateCrossrefRepository
    )

    @Test
    fun `test should return 500 on object receive error`() = runBlocking {
        val receiveException = Exception("Stub1") // prepare exception
        // receiving object should cause error
        val mockServiceCall = mockk<ServiceCall>(relaxed = true)
        coEvery { mockServiceCall.receive<NetworkCreateDevice>() } returns Either.Right(receiveException)
        // handle call
        service.handle(mockServiceCall)
        // check failure response was returned
        val failureResponse = NetworkCreatedDeviceResponse.Failure(receiveException)
        coVerify { mockServiceCall.respond(HttpStatusCode.InternalServerError, failureResponse) }
    }

    @Test
    fun `test should return 500 on device creation error`() = runBlocking {
        val networkCreateDevice = NetworkCreateDevice(sensors = listOf(NetworkCreateSensor()))
        // receiving object should cause error
        val mockServiceCall = mockk<ServiceCall>(relaxed = true)
        coEvery { mockServiceCall.receive<NetworkCreateDevice>() } returns Either.Left(networkCreateDevice)

        val createDeviceException = Exception("Stub!")
        every { mockCreateDeviceRepository.createDevice(any()) } returns Either.Right(createDeviceException)

        val createdSensor = CreatedSensor(Sensor(SensorId("sas"), "title"))
        every { mockCreateSensorRepository.create(any()) } returns Either.Left(createdSensor)
        // handle call
        service.handle(mockServiceCall)
        // check failure response was returned
        val failureResponse = NetworkCreatedDeviceResponse.Failure(createDeviceException)
        coVerify { mockServiceCall.respond(HttpStatusCode.InternalServerError, failureResponse) }
    }

    @Test
    fun `test should return 500 on sensor creation error`() = runBlocking {
        val networkCreateDevice = NetworkCreateDevice(sensors = listOf(NetworkCreateSensor()))
        // receiving object should cause error
        val mockServiceCall = mockk<ServiceCall>(relaxed = true)
        coEvery { mockServiceCall.receive<NetworkCreateDevice>() } returns Either.Left(networkCreateDevice)

        val createdDevice = CreatedDevice(CreateDevice(null, ""), Device(DeviceId("sas"), ""))
        every { mockCreateDeviceRepository.createDevice(any()) } returns Either.Left(createdDevice)

        val createSensorException = Exception("Stub!")
        every { mockCreateSensorRepository.create(any()) } returns Either.Right(createSensorException)
        // handle call
        service.handle(mockServiceCall)
        // check failure response was returned
        val failureResponse = NetworkCreatedDeviceResponse.Failure(createSensorException)
        coVerify { mockServiceCall.respond(HttpStatusCode.InternalServerError, failureResponse) }
    }

    @Test
    fun `test should return 500 on crossref creation error`() = runBlocking {
        val networkCreateDevice = NetworkCreateDevice(sensors = listOf(NetworkCreateSensor()))
        // receiving object should cause error
        val mockServiceCall = mockk<ServiceCall>(relaxed = true)
        coEvery { mockServiceCall.receive<NetworkCreateDevice>() } returns Either.Left(networkCreateDevice)

        val createdDevice = CreatedDevice(CreateDevice(null, ""), Device(DeviceId("sas"), ""))
        every { mockCreateDeviceRepository.createDevice(any()) } returns Either.Left(createdDevice)

        val createdSensor = CreatedSensor(Sensor(SensorId("sas"), "title"))
        every { mockCreateSensorRepository.create(any()) } returns Either.Left(createdSensor)

        val createCrossrefException = Exception("Stub!")
        every {
            mockCreateCrossrefRepository.createCrossrefs(createdDevice.device.id, listOf(createdSensor.sensor.id))
        } returns Either.Right(createCrossrefException)
        // handle call
        service.handle(mockServiceCall)
        // check failure response was returned
        val failureResponse = NetworkCreatedDeviceResponse.Failure(createCrossrefException)
        coVerify { mockServiceCall.respond(HttpStatusCode.InternalServerError, failureResponse) }
    }

    @Test
    fun `test should return 200 on repository success`() = runBlocking {
        val networkCreateDevice = NetworkCreateDevice(sensors = listOf(NetworkCreateSensor()))
        // receiving object should cause error
        val mockServiceCall = mockk<ServiceCall>(relaxed = true)
        coEvery { mockServiceCall.receive<NetworkCreateDevice>() } returns Either.Left(networkCreateDevice)

        val createdDevice = CreatedDevice(CreateDevice(null, ""), Device(DeviceId("sas"), ""))
        every { mockCreateDeviceRepository.createDevice(any()) } returns Either.Left(createdDevice)

        val createdSensor = CreatedSensor(Sensor(SensorId("sas"), "title"))
        every { mockCreateSensorRepository.create(any()) } returns Either.Left(createdSensor)

        every {
            mockCreateCrossrefRepository.createCrossrefs(createdDevice.device.id, listOf(createdSensor.sensor.id))
        } returns Either.Left(mockk())
        // handle call
        service.handle(mockServiceCall)
        // check failure response was returned
        val successResponse = NetworkCreatedDeviceResponse.Success(createdDevice, listOf(createdSensor))
        coVerify { mockServiceCall.respond(HttpStatusCode.OK, successResponse) }
    }
}