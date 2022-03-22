package com.makentoshe.shuvi.repository.sensor.value

import com.makentoshe.shuvi.common.Either
import com.makentoshe.shuvi.entity.SensorId
import com.makentoshe.shuvi.entity.sensor.value.CreateSensorValue
import com.makentoshe.shuvi.entity.sensor.value.CreatedValue
import com.makentoshe.shuvi.entity.sensor.value.Value
import com.makentoshe.shuvi.repository.Repository

interface ValueRepository : Repository {

    fun create(value: CreateSensorValue): CreateValueResponse

    // TODO rework with optional
    fun all(limit: Int): AllValueResponse

    fun sensor(sensorId: SensorId, limit: Int): SensorValueResponse
}

typealias CreateValueResponse = Either<CreatedValue, Exception>

typealias AllValueResponse = Either<List<Value>, Exception>

typealias SensorValueResponse = Either<List<Value>, Exception>
