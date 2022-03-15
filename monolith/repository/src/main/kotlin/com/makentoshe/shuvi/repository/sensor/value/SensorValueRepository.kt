package com.makentoshe.shuvi.repository.sensor.value

import com.makentoshe.shuvi.entity.sensor.value.CreateSensorValue
import com.makentoshe.shuvi.repository.Repository
import com.makentoshe.shuvi.response.repository.sensor.value.CreateSensorValueResponse

interface SensorValueRepository: Repository {
    fun create(value: CreateSensorValue): CreateSensorValueResponse
}