package com.makentoshe.shuvi.repository.sensor.value

import com.makentoshe.shuvi.common.Either
import com.makentoshe.shuvi.common.database.ValueIdGenerator
import com.makentoshe.shuvi.common.left
import com.makentoshe.shuvi.database.Database
import com.makentoshe.shuvi.entity.SensorId
import com.makentoshe.shuvi.entity.database.value.DatabaseInsertedValue
import com.makentoshe.shuvi.entity.database.value.DatabaseValue
import com.makentoshe.shuvi.entity.database.value.toValue
import com.makentoshe.shuvi.entity.sensor.value.CreateSensorValue
import com.makentoshe.shuvi.entity.sensor.value.SensorValueId
import io.mockk.every
import io.mockk.mockk
import io.mockk.spyk
import io.mockk.verify
import javax.xml.crypto.Data
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class SensorValueRepositoryImplTest {

    private val mockDatabase = mockk<Database>()
    private val spykGenerator = spyk(valueIdGenerator(SensorValueId("generated")))
    private val repository = ValueRepositoryImpl(mockDatabase, spykGenerator)

    @Test
    fun `test should return exception on already created value`() {
        val createValue = CreateSensorValue(SensorValueId("id"), SensorId("123"), 321)

        // sensor should exist in database
        every { mockDatabase.value().get().id(SensorValueId(any())) } returns Either.Left(mockk())

        // check repository action is failure
        assert(repository.create(createValue).isRight())
    }

    @Test
    fun `test should return exception on insertion issue`() {
        val createValue = CreateSensorValue(SensorValueId("id"), SensorId("123"), 321)

        // value shouldn't exists in database
        every { mockDatabase.value().get().id(SensorValueId(any())) } returns Either.Right(Exception())

        // insertion should be failure
        every { mockDatabase.value().insert().value(any()) } returns Either.Right(Exception("Stub!"))

        // check repository action is failure
        assert(repository.create(createValue).isRight())
    }

    @Test
    fun `test should create value with provided id`() {
        val valueId = SensorValueId("id")
        val createValue = CreateSensorValue(valueId, SensorId("id"), 123)

        // sensor shouldn't exists in database
        every {
            mockDatabase.value().get().id(SensorValueId(match { it == valueId.string }))
        } returns Either.Right(Exception())

        // insertion should be successful
        val databaseInsertedValue = DatabaseInsertedValue(DatabaseValue(valueId.string, "id", 123, ""))
        every { mockDatabase.value().insert().value(any()) } returns Either.Left(databaseInsertedValue)

        // check repository action is successful
        val left = repository.create(createValue).left()
        assertEquals(left.createSensorValue, createValue)
        assertEquals(left.value.valueId.string, valueId.string)
    }

    @Test
    fun `test should create sensor without provided id`() {
        val createValue = CreateSensorValue(null, SensorId("id"), 123)

        // sensor shouldn't exists in database
        every {
            mockDatabase.value().get().id(SensorValueId(any()))
        } returns Either.Right(Exception())

        // insertion should be successful
        val databaseInsertedValue = DatabaseInsertedValue(DatabaseValue("Stub1", "id", 123, ""))
        every { mockDatabase.value().insert().value(any()) } returns Either.Left(databaseInsertedValue)

        // check repository action is successful
        val left = repository.create(createValue).left()
        assertEquals(left.createSensorValue, createValue)
        // check id generation was called (we can't check the value due to stubbed insertion result)
        verify(exactly = 1) { spykGenerator.generate }
    }

    @Test
    fun `test should return all values from database`() {
        // database request should be successful
        val databaseValueList = buildList<DatabaseValue>(13) {
            DatabaseValue("", "", 1, "") }
        every { mockDatabase.value().get().all() } returns Either.Left(databaseValueList)

        // check repository action is successful
        val left = repository.all().left()
        assertEquals(databaseValueList.map { it.toValue() }, left)
    }

    private fun valueIdGenerator(valueId: SensorValueId): ValueIdGenerator {
        return spyk<ValueIdGenerator>(object : ValueIdGenerator {
            override val generate: SensorValueId = valueId
        })
    }
}