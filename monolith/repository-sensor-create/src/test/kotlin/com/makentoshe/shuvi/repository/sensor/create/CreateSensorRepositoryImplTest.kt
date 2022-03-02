package com.makentoshe.shuvi.repository.sensor.create

import com.makentoshe.shuvi.common.Either
import com.makentoshe.shuvi.common.database.SensorIdGenerator
import com.makentoshe.shuvi.database.Database
import com.makentoshe.shuvi.entity.CreateSensor
import com.makentoshe.shuvi.entity.SensorId
import com.makentoshe.shuvi.entity.database.DatabaseInsertedSensor
import com.makentoshe.shuvi.entity.database.DatabaseSensor
import io.mockk.every
import io.mockk.mockk
import io.mockk.spyk
import io.mockk.verify
import org.junit.jupiter.api.Test

class CreateSensorRepositoryImplTest {

    private val mockDatabase = mockk<Database>()
    private val spykGenerator = spyk(sensorIdGenerator(SensorId("generated")))
    private val repository = CreateSensorRepositoryImpl(mockDatabase, spykGenerator)

    @Test
    fun `test should return exception on already created sensor`() {
        val createSensor = CreateSensor(SensorId("id"), "title")

        // sensor should exist in database
        every { mockDatabase.sensor().get().id(SensorId(any())) } returns Either.Left(mockk())

        // check repository action is failure
        assert(repository.create(createSensor).isRight())
    }

    @Test
    fun `test should return exception on insertion issue`() {
        val createSensor = CreateSensor(SensorId("id"), "title")

        // sensor shouldn't exists in database
        every { mockDatabase.sensor().get().id(SensorId(any())) } returns Either.Right(Exception("Sensor not found"))

        // insertion should be failure
        every { mockDatabase.sensor().insert().insertSensor(any()) } returns Either.Right(Exception("Stub!"))

        // check repository action is failure
        assert(repository.create(createSensor).isRight())
    }

    @Test
    fun `test should create sensor with provided id`() {
        val sensorId = SensorId("id")
        val createSensor = CreateSensor(SensorId("id"), "title")

        // sensor shouldn't exists in database
        every { mockDatabase.sensor().get().id(SensorId(match { it == sensorId.string })) } returns Either.Right(
            Exception("Sensor not found")
        )

        // insertion should be successful
        val databaseInsertedSensor = DatabaseInsertedSensor(DatabaseSensor(sensorId.string, createSensor.title))
        every { mockDatabase.sensor().insert().insertSensor(any()) } returns Either.Left(databaseInsertedSensor)

        // check repository action is successful
        assert(repository.create(createSensor).isLeft())
    }

    @Test
    fun `test should create sensor without provided id`() {
        val createSensor = CreateSensor(null, "title")

        // sensor shouldn't exists in database
        every { mockDatabase.sensor().get().id(SensorId(any())) } returns Either.Right(Exception("Sensor not found"))

        // insertion should be successful
        val databaseInsertedSensor = DatabaseInsertedSensor(DatabaseSensor("stub", createSensor.title))
        every { mockDatabase.sensor().insert().insertSensor(any()) } returns Either.Left(databaseInsertedSensor)

        // check repository action is successful
        assert(repository.create(createSensor).isLeft())
        // check id generation was called (we can't check the value due to stubbed insertion result)
        verify(exactly = 1) { spykGenerator.generate }
    }

    private fun sensorIdGenerator(value: SensorId): SensorIdGenerator {
        return spyk<SensorIdGenerator>(object : SensorIdGenerator {
            override val generate: SensorId = value
        })
    }
}