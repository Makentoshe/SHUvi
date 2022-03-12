package com.makentoshe.shuvi.service.device

import com.makentoshe.shuvi.common.Either
import com.makentoshe.shuvi.entity.DeviceId
import com.makentoshe.shuvi.entity.device.Device
import com.makentoshe.shuvi.entity.service.NetworkDevice2
import com.makentoshe.shuvi.repository.GetDeviceRepository
import com.makentoshe.shuvi.response.service.NetworkGetDevicesResponse
import com.makentoshe.shuvi.service.ServiceCall
import io.ktor.http.HttpStatusCode
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

class DeviceServiceImplTest {

    private val mockRepository = mockk<GetDeviceRepository>()
    private val service = DeviceServiceImpl(mockRepository)

    @Test
    fun `test should return 500 on parameter receive error`() = runBlocking {
        // receiving parameter should cause error
        val mockServiceCall = mockk<ServiceCall>(relaxed = true)
        every { mockServiceCall.parameters[service.deviceIdParameter] } returns null
        // handle call
        service.handle(mockServiceCall)
        // check failure response was returned
        coVerify {
            mockServiceCall.respond(HttpStatusCode.InternalServerError, ofType<NetworkGetDevicesResponse.Failure>())
        }
    }

    @Test
    fun `test should return 500 on repository error`() = runBlocking {
        val deviceIdParameter = "device-id-sas"
        val repositoryException = Exception("Stub1") // prepare exception
        // receiving object should cause error
        val mockServiceCall = mockk<ServiceCall>(relaxed = true)
        every { mockServiceCall.parameters[service.deviceIdParameter] } returns deviceIdParameter
        every { mockRepository.getDevice(DeviceId(deviceIdParameter)) } returns Either.Right(repositoryException)
        // handle call
        service.handle(mockServiceCall)
        // check failure response was returned
        coVerify {
            mockServiceCall.respond(HttpStatusCode.InternalServerError, ofType<NetworkGetDevicesResponse.Failure>())
        }
    }

    @Test
    fun `test should return 200 on repository success`() = runBlocking {
        // prepare additional objects
        val deviceIdParameter = "device-id-sas"
        val device = Device(DeviceId(deviceIdParameter), "")
        // receiving object should cause error
        val mockServiceCall = mockk<ServiceCall>(relaxed = true)
        every { mockServiceCall.parameters[service.deviceIdParameter] } returns deviceIdParameter
        every { mockRepository.getDevice(DeviceId(deviceIdParameter)) } returns Either.Left(device)
        // handle call
        service.handle(mockServiceCall)
        // check failure response was returned
        val successResponse = NetworkGetDevicesResponse.Success(listOf(NetworkDevice2(device)))
        coVerify { mockServiceCall.respond(HttpStatusCode.OK, successResponse) }
    }

}