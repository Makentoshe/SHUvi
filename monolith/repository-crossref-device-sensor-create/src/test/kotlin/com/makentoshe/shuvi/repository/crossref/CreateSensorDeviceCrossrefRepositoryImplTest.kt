package com.makentoshe.shuvi.repository.crossref

import com.makentoshe.shuvi.common.Either
import com.makentoshe.shuvi.common.left
import com.makentoshe.shuvi.database.Database
import com.makentoshe.shuvi.entity.DeviceId
import com.makentoshe.shuvi.entity.SensorId
import com.makentoshe.shuvi.entity.database.crossref.DatabaseDeviceSensorsCrossref
import com.makentoshe.shuvi.entity.database.crossref.DatabaseExistsDeviceSensorsCrossref
import com.makentoshe.shuvi.entity.database.crossref.DatabaseInsertedDeviceSensorsCrossrefs
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class CreateSensorDeviceCrossrefRepositoryImplTest {

    private val mockDatabase = mockk<Database>()
    private val repository = CreateSensorDeviceCrossrefRepositoryImpl(mockDatabase)

    @Test
    fun `test should return exception on empty sensors argument`() {
        val deviceId = DeviceId("id")
        val sensorIds = emptyList<SensorId>()

        assert(repository.createCrossrefs(deviceId, sensorIds).isRight())
    }

    @Test
    fun `test should insert only unexisted crossrefs`() {
        val deviceId = DeviceId("id")
        val sensorIds = listOf(SensorId("1"), SensorId("2"))

        val existedCrossref = existsCrossrefResponse(deviceId, sensorIds[0], true)
        val unexistedCrossref = existsCrossrefResponse(deviceId, sensorIds[1], false)

        // Only crossref between device and sensor1 should exist
        every {
            mockDatabase.crossref().device2Sensors().exists().crossref(any())
        } returns existedCrossref andThen unexistedCrossref

        // inserting crossref between device and sensor1 should be ok
        every {
            mockDatabase.crossref().device2Sensors().insert().insertCrossrefs(match { it.first().let { it.deviceId == deviceId.string && it.sensorId == sensorIds[1].string} })
        } returns Either.Left(DatabaseInsertedDeviceSensorsCrossrefs(listOf(databaseCrossref(deviceId, sensorIds[1]))))

        val left = repository.createCrossrefs(deviceId, sensorIds).left()
        assertEquals(deviceId, left.first().deviceId)
        assertEquals(sensorIds[1], left.first().sensorId)
    }

    private fun existsCrossrefResponse(deviceId: DeviceId, sensorId: SensorId, exists: Boolean) = Either.Left(
        DatabaseExistsDeviceSensorsCrossref(databaseCrossref(deviceId, sensorId), exists)
    )

    private fun databaseCrossref(deviceId: DeviceId, sensorId: SensorId): DatabaseDeviceSensorsCrossref {
        return DatabaseDeviceSensorsCrossref(deviceId.string, sensorId.string)
    }
}