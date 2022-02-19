package com.makentoshe.shuvi.repository.device.create

import com.makentoshe.shuvi.common.Either
import com.makentoshe.shuvi.common.left
import com.makentoshe.shuvi.common.right
import com.makentoshe.shuvi.database.Database
import com.makentoshe.shuvi.entity.*
import com.makentoshe.shuvi.entity.database.*
import com.makentoshe.shuvi.entity.database.crossref.DatabaseDeviceSensorsCrossref
import com.makentoshe.shuvi.entity.database.crossref.DatabaseInsertedDeviceSensorsCrossrefs
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class CreateDeviceRepositoryImplTest {

    private val sensor1 = Sensor(SensorId("sensor1"))
    private val sensor2 = Sensor(SensorId("sensor2"))
    private val sensor3 = Sensor(SensorId("sensor3"))
    private val sensors = listOf(sensor1, sensor2, sensor3)

    private val mockDatabase = MockDatabase(mockk())
    private val repository = CreateDeviceRepositoryImpl(mockDatabase.database)

    @Test
    fun testShouldReturnExceptionOnAlreadyCreatedDevice() {
        // Device to add into the database
        val device = createDevice()
        // Device is exists in database
        mockDatabase.`device should exists in database`(device)

        // Should return error
        assert(repository.createDevice(device.toCreateDevice()).isRight())
    }

    @Test
    fun testShouldReturnExceptionOnSensorsGetIssue() {
        // Device to add into the database
        val device = createDevice(sensors)
        // Device doesn't exist in the database
        mockDatabase.`device shouldnt exists in database`(device)

        // will be thrown on sensors getting
        val sensorsException = Exception("Sensors get issue!")
        // Sensors searching in the database
        mockDatabase.`sensors getting should cause exception`(sensors, sensorsException)

        // Check exception was handled
        assertEquals(sensorsException, repository.createDevice(device.toCreateDevice()).right())
    }

    @Test
    fun testShouldReturnExceptionOnSensorInsertIssue() {
        // Device to add into the database
        val device = createDevice(sensors)
        // Device doesn't exist in the database
        mockDatabase.`device shouldnt exists in database`(device)

        // Will be returned on sensors getting, but sensor1 and sensor3 were missed
        val databaseGetSensors = DatabaseGetSensors(sensors.map { it.id }, listOf(sensor2))
        // Sensors searching in the database
        mockDatabase.`sensors getting should be successful`(sensors, databaseGetSensors)

        // Will be thrown on sensor insert
        val exception = Exception("Sensor insert issue!")
        // Inserting a sensor cause an issue
        mockDatabase.`sensors insertion should cause exception`(sensor1, exception)
        mockDatabase.`sensors insertion should cause exception`(sensor3, exception)

        // Check exception was handled
        assertEquals(exception, repository.createDevice(device.toCreateDevice()).right())
    }

    @Test
    fun testShouldReturnExceptionOnCrossrefInsertIssue() {
        // Device to add into the database
        val device = createDevice(sensors)
        // Device doesn't exist in the database
        mockDatabase.`device shouldnt exists in database`(device)

        // Will be returned on sensors getting, but sensor1 and sensor3 were missed
        val databaseGetSensors = DatabaseGetSensors(sensors.map { it.id }, listOf(sensor2))
        // Sensors searching in the database
        mockDatabase.`sensors getting should be successful`(sensors, databaseGetSensors)

        // Missed sensors should be inserted into the database
        mockDatabase.`sensors insertion should be successful`(sensor1, DatabaseInsertedSensor(DatabaseSensor(sensor1)))
        mockDatabase.`sensors insertion should be successful`(sensor3, DatabaseInsertedSensor(DatabaseSensor(sensor3)))

        // Will be thrown on crossref insert
        val exception = Exception("Crossrefs insert issue!")
        // Inserting a crossref should cause an exception
        mockDatabase.`crossrefs insertion should cause exception`(listOf(sensor1, sensor3), exception)

        // Check exception was handled
        assertEquals(exception, repository.createDevice(device.toCreateDevice()).right())
    }

    @Test
    fun testShouldReturnExceptionOnDeviceInsertIssue() {
        // Device to add into the database
        val device = createDevice(sensors)
        // Device doesn't exist in the database
        mockDatabase.`device shouldnt exists in database`(device)

        // Will be returned on sensors getting, but sensor1 and sensor3 were missed
        val databaseGetSensors = DatabaseGetSensors(sensors.map { it.id }, listOf(sensor2))
        // Sensors searching in the database
        mockDatabase.`sensors getting should be successful`(sensors, databaseGetSensors)

        // Missed sensors should be inserted into the database
        mockDatabase.`sensors insertion should be successful`(sensor1, DatabaseInsertedSensor(DatabaseSensor(sensor1)))
        mockDatabase.`sensors insertion should be successful`(sensor3, DatabaseInsertedSensor(DatabaseSensor(sensor3)))

        // These crossrefs should be inserted along with the missed sensors and should be matched with their ids
        val crossref1 = DatabaseDeviceSensorsCrossref(device.id.string, sensor1.id.string)
        val crossref3 = DatabaseDeviceSensorsCrossref(device.id.string, sensor3.id.string)
        val insertedCrossrefs = DatabaseInsertedDeviceSensorsCrossrefs(listOf(crossref1, crossref3))
        // Inserting a crossrefs into the database
        mockDatabase.`crossrefs insertion should be successful`(listOf(sensor1, sensor3), insertedCrossrefs)

        // Will be thrown on device insert
        val exception = Exception("Device insert issue!")
        // Inserting a device should cause an exception
        mockDatabase.`device insertion should cause exception`(device, exception)

        // Check exception was handled
        assertEquals(exception, repository.createDevice(device.toCreateDevice()).right())
    }

    @Test
    fun testShouldCreateMissedSensorRecordsInDatabase() {
        // Device to add into the database
        val device = createDevice(sensors)
        // Device doesn't exist in the database
        mockDatabase.`device shouldnt exists in database`(device)

        // Will be returned on sensors getting, but sensor1 and sensor3 were missed
        val databaseGetSensors = DatabaseGetSensors(sensors.map { it.id }, listOf(sensor2))
        // Sensors searching in the database
        mockDatabase.`sensors getting should be successful`(sensors, databaseGetSensors)

        // Missed sensors should be inserted into the database
        mockDatabase.`sensors insertion should be successful`(sensor1, DatabaseInsertedSensor(DatabaseSensor(sensor1)))
        mockDatabase.`sensors insertion should be successful`(sensor3, DatabaseInsertedSensor(DatabaseSensor(sensor3)))

        // These crossrefs should be inserted along with the missed sensors and should be matched with their ids
        val crossref1 = DatabaseDeviceSensorsCrossref(device.id.string, sensor1.id.string)
        val crossref3 = DatabaseDeviceSensorsCrossref(device.id.string, sensor3.id.string)
        val insertedCrossrefs = DatabaseInsertedDeviceSensorsCrossrefs(listOf(crossref1, crossref3))
        // Inserting crossrefs into the database
        mockDatabase.`crossrefs insertion should be successful`(listOf(sensor1, sensor3), insertedCrossrefs)

        // Inserting device
        mockDatabase.`device insertion should be successful`(device, InsertedDatabaseDevice(DatabaseDevice(device), true))

        // Check device was created
        val createdDevice = repository.createDevice(device.toCreateDevice()).left()
        assertEquals(device, createdDevice.device)
    }

    @Test // TODO this is a simple mockup till device id generation wasn't extracted to other class
    fun testShouldCreateDeviceRecordsWithoutProvidedIdInDatabase() {
        // Device to add into the database
        val device = createDevice(sensors)
        // Device doesn't exist in the database
        every { mockDatabase.database.device().get().id(DeviceId(any())) } returns Either.Right(Exception("Device not found"))

        // Will be returned on sensors getting, but sensor1 and sensor3 were missed
        val databaseGetSensors = DatabaseGetSensors(sensors.map { it.id }, listOf(sensor2))
        // Sensors searching in the database
        mockDatabase.`sensors getting should be successful`(sensors, databaseGetSensors)

        // Missed sensors should be inserted into the database
        mockDatabase.`sensors insertion should be successful`(sensor1, DatabaseInsertedSensor(DatabaseSensor(sensor1)))
        mockDatabase.`sensors insertion should be successful`(sensor3, DatabaseInsertedSensor(DatabaseSensor(sensor3)))

        // These crossrefs should be inserted along with the missed sensors and should be matched with their ids
        val crossref1 = DatabaseDeviceSensorsCrossref(device.id.string, sensor1.id.string)
        val crossref3 = DatabaseDeviceSensorsCrossref(device.id.string, sensor3.id.string)
        val insertedCrossrefs = DatabaseInsertedDeviceSensorsCrossrefs(listOf(crossref1, crossref3))
        // Inserting crossrefs into the database
        mockDatabase.`crossrefs insertion should be successful`(listOf(sensor1, sensor3), insertedCrossrefs)

        // Inserting device
        every {
            mockDatabase.database.device().insert().insertDevice(any())
        } returns Either.Left(InsertedDatabaseDevice(DatabaseDevice(device), true))

        // Check device was created
        val createdDevice = repository.createDevice(device.toCreateDevice().copy(id = null)).left()
        assert(createdDevice.device.id.string.length == 8)
    }

    private fun createDevice(sensors: List<Sensor> = emptyList()) =
        Device(DeviceId("id"), "DeviceTitle", sensors)

    private fun MockDatabase.`device shouldnt exists in database`(
        device: Device,
    ) = every {
        database.device().get().id(device.id)
    } returns Either.Right(Exception("Device not found"))

    private fun MockDatabase.`device should exists in database`(
        device: Device,
    ) = every {
        database.device().get().id(device.id)
    } returns Either.Left(DatabaseDevice(device))

    private fun MockDatabase.`sensors getting should cause exception`(
        sensors: List<Sensor>,
        exception: Exception,
    ) = every {
        database.sensor().get().ids(sensors.map { it.id })
    } returns Either.Right(exception)

    private fun MockDatabase.`sensors getting should be successful`(
        sensors: List<Sensor>,
        databaseGetSensors: DatabaseGetSensors,
    ) = every {
        database.sensor().get().ids(sensors.map { it.id })
    } returns Either.Left(databaseGetSensors)

    private fun MockDatabase.`sensors insertion should cause exception`(
        sensor: Sensor,
        exception: Exception,
    ) = every {
        database.sensor().insert().insertSensor(match { it.id == sensor.id.string })
    } returns Either.Right(exception)

    private fun MockDatabase.`sensors insertion should be successful`(
        sensor: Sensor,
        databaseInsertedSensor: DatabaseInsertedSensor,
    ) = every {
        database.sensor().insert().insertSensor(match { it.id == sensor.id.string })
    } returns Either.Left(databaseInsertedSensor)

    private fun MockDatabase.`crossrefs insertion should cause exception`(
        missed: List<Sensor>,
        exception: Exception,
    ) = every {
        database.crossref().device2Sensors().insert().insertCrossrefs(match { crossrefs ->
            crossrefs.map { crossref -> crossref.sensorId }.containsAll(missed.map { sensor -> sensor.id.string })
        })
    } returns Either.Right(exception)

    private fun MockDatabase.`crossrefs insertion should be successful`(
        missed: List<Sensor>,
        databaseInsertedDeviceSensorsCrossrefs: DatabaseInsertedDeviceSensorsCrossrefs
    ) = every {
        database.crossref().device2Sensors().insert().insertCrossrefs(match { crossrefs ->
            crossrefs.map { crossref -> crossref.sensorId }.containsAll(missed.map { sensor -> sensor.id.string })
        })
    } returns Either.Left(databaseInsertedDeviceSensorsCrossrefs)

    private fun MockDatabase.`device insertion should cause exception`(
        device: Device,
        exception: Exception,
    ) = every {
        database.device().insert().insertDevice(match { databaseDevice -> databaseDevice.id == device.id.string })
    } returns Either.Right(exception)

    private fun MockDatabase.`device insertion should be successful`(
        device: Device,
        insertedDatabaseDevice: InsertedDatabaseDevice
    ) = every {
        database.device().insert().insertDevice(match { databaseDevice -> databaseDevice.id == device.id.string })
    } returns Either.Left(insertedDatabaseDevice)

    private fun Device.toCreateDevice() = CreateDevice(id, title, sensors)
}

@JvmInline
internal value class MockDatabase(val database: Database)