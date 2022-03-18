package com.makentoshe.shuvi.database.value

import com.makentoshe.shuvi.common.Either
import com.makentoshe.shuvi.entity.database.value.DatabaseValue
import com.makentoshe.shuvi.entity.sensor.value.SensorValueId

interface GetValueDatabase {
    fun id(value: SensorValueId): DatabaseGetValueResponse
    fun all(limit: Int = -1): DatabaseGetValuesResponse
}

typealias DatabaseGetValueResponse = Either<DatabaseValue, Exception>

typealias DatabaseGetValuesResponse = Either<List<DatabaseValue>, Exception>

