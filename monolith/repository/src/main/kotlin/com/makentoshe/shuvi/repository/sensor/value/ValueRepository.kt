package com.makentoshe.shuvi.repository.sensor.value

import com.makentoshe.shuvi.common.Either
import com.makentoshe.shuvi.entity.sensor.value.CreateSensorValue
import com.makentoshe.shuvi.entity.sensor.value.CreatedSensorValue
import com.makentoshe.shuvi.entity.sensor.value.Value
import com.makentoshe.shuvi.repository.Repository

interface ValueRepository : Repository {

    fun create(value: CreateSensorValue): CreateSensorValueResponse

    // TODO rework with optional
    fun all(limit: Int): AllSensorValueResponse
}

typealias CreateSensorValueResponse = Either<CreatedSensorValue, Exception>

typealias AllSensorValueResponse = Either<List<Value>, Exception>
