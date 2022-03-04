package com.makentoshe.shuvi.repository.device.delete

import com.makentoshe.shuvi.common.Either
import com.makentoshe.shuvi.database.Database
import com.makentoshe.shuvi.entity.DeviceId
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test

class DeleteDeviceRepositoryImplTest {

    private val mockDatabase = mockk<Database>()
    private val repository = DeleteDeviceRepositoryImpl(mockDatabase)

    @Test
    fun `test should delete device if it exists`() {
        val deviceId = DeviceId("id")

        // device should be deleted from database
        every { mockDatabase.device().delete().id(deviceId) } returns Either.Left(mockk(relaxed = true))

        // check response
        assert(repository.deleteDevice(deviceId).isLeft())
    }

    @Test
    fun `test should return exception if device to delete doesnt exists`() {
        val deviceId = DeviceId("id")

        // device shouldnt be deleted from database because it isn't exists
        every { mockDatabase.device().delete().id(deviceId) } returns Either.Right(Exception("Stub"))

        // check response
        assert(repository.deleteDevice(deviceId).isRight())
    }

}