package com.makentoshe.shuvi.repository.device.create

import com.makentoshe.shuvi.common.Either
import com.makentoshe.shuvi.common.database.DeviceIdGenerator
import com.makentoshe.shuvi.database.Database
import com.makentoshe.shuvi.entity.DeviceId
import com.makentoshe.shuvi.entity.database.*
import com.makentoshe.shuvi.entity.device.CreateDevice
import io.mockk.every
import io.mockk.mockk
import io.mockk.spyk
import io.mockk.verify
import org.junit.jupiter.api.Test

class CreateDeviceRepositoryImplTest {

    private val mockDatabase = mockk<Database>()
    private val spykGenerator = spyk(deviceIdGenerator(DeviceId("generated")))
    private val repository = CreateDeviceRepositoryImpl(mockDatabase, spykGenerator)

    @Test
    fun `test should return exception on already created device`() {
        val createDevice = CreateDevice(DeviceId("id"), "title")

        // device shouldn't exists in database
        every { mockDatabase.device().get().id(DeviceId(any())) } returns Either.Left(mockk())

        // Should return error
        assert(repository.createDevice(createDevice).isRight())
    }

    @Test
    fun `test should return exception on device insertion issue`() {
        val createDevice = CreateDevice(DeviceId("id"), "title")

        // device shouldn't exists in database
        every { mockDatabase.device().get().id(DeviceId(any())) } returns Either.Right(Exception("Device not found"))

        // insertion should be failure
        every { mockDatabase.device().insert().insertDevice(any()) } returns Either.Right(Exception("Insert issue"))

        // should return an error
        assert(repository.createDevice(createDevice).isRight())
    }

    @Test
    fun `test should create device with provided id`() {
        val createDevice = CreateDevice(DeviceId("id"), "title")

        // device shouldn't exists in database
        every { mockDatabase.device().get().id(DeviceId(any())) } returns Either.Right(Exception("Device not found"))

        // insertion should be successful
        val databaseInsertedDevice = InsertedDatabaseDevice(DatabaseDevice("stub", createDevice.title))
        every { mockDatabase.device().insert().insertDevice(any()) } returns Either.Left(databaseInsertedDevice)

        // check repository action is successful
        assert(repository.createDevice(createDevice).isLeft())
    }

    @Test
    fun `test should create device without provided id`() {
        val createDevice = CreateDevice(null, "title")

        // device shouldn't exists in database
        every { mockDatabase.device().get().id(DeviceId(any())) } returns Either.Right(Exception("Device not found"))

        // insertion should be successful
        val databaseInsertedDevice = InsertedDatabaseDevice(DatabaseDevice("stub", createDevice.title))
        every { mockDatabase.device().insert().insertDevice(any()) } returns Either.Left(databaseInsertedDevice)

        // check repository action is successful
        assert(repository.createDevice(createDevice).isLeft())
        // check id generation was called (we can't check the value due to stubbed insertion result)
        verify(exactly = 1) { spykGenerator.generate }
    }

    private fun deviceIdGenerator(value: DeviceId): DeviceIdGenerator {
        return spyk<DeviceIdGenerator>(object : DeviceIdGenerator {
            override val generate: DeviceId = value
        })
    }
}