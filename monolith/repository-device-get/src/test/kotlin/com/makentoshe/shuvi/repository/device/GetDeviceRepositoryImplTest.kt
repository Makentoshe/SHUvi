package com.makentoshe.shuvi.repository.device

import com.makentoshe.shuvi.common.Either
import com.makentoshe.shuvi.database.Database
import com.makentoshe.shuvi.entity.DeviceId
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test

class GetDeviceRepositoryImplTest {

    private val mockDatabase = mockk<Database>()
    private val repository = GetDeviceRepositoryImpl(mockDatabase)

    @Test
    fun `test should return exception if device doesnt exists`() {
        val deviceId = DeviceId("id")

        // device shouldnt exist in database
        every { mockDatabase.device().get().id(deviceId) } returns Either.Right(Exception("Stub"))

        // check the response
        assert(repository.getDevice(deviceId).isRight())
    }

    @Test
    fun `test should be successful if device exists`() {
        val deviceId = DeviceId("id")

        // device should exist in database
        every { mockDatabase.device().get().id(deviceId) } returns Either.Left(mockk(relaxed = true))

        // check the response
        assert(repository.getDevice(deviceId).isLeft())
    }
}