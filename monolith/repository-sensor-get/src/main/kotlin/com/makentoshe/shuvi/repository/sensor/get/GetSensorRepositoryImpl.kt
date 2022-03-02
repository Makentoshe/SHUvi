package com.makentoshe.shuvi.repository.sensor.get

import com.makentoshe.shuvi.database.Database
import com.makentoshe.shuvi.entity.SensorId
import com.makentoshe.shuvi.repository.sensor.GetSensorRepository
import com.makentoshe.shuvi.response.repository.sensor.GetSensorResponse

class GetSensorRepositoryImpl(private val database: Database): GetSensorRepository {
    override fun id(sensorId: SensorId) : GetSensorResponse {
        return database.sensor().get().id(sensorId).mapLeft { databaseSensor -> databaseSensor.toSensor() }
    }
}