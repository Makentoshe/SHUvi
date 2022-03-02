package com.makentoshe.shuvi.repository.sensor

import com.makentoshe.shuvi.entity.CreateSensor
import com.makentoshe.shuvi.response.repository.sensor.CreateSensorResponse

interface CreateSensorRepository {
    fun create(sensor: CreateSensor): CreateSensorResponse
}