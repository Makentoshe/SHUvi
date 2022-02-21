package com.makentoshe.shuvi.repository.sensor

import com.makentoshe.shuvi.entity.SensorId
import com.makentoshe.shuvi.response.repository.sensor.GetSensorResponse

interface GetSensorRepository {
    fun id(sensorId: SensorId): GetSensorResponse
}