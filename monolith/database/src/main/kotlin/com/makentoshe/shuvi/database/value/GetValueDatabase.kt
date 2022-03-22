package com.makentoshe.shuvi.database.value

import com.makentoshe.shuvi.common.Either
import com.makentoshe.shuvi.entity.SensorId
import com.makentoshe.shuvi.entity.database.value.DatabaseValue
import com.makentoshe.shuvi.entity.sensor.value.SensorValueId

interface GetValueDatabase {

    fun id(value: SensorValueId): DatabaseGetValueResponse

    fun all(limit: Int): DatabaseGetAllValuesResponse

    fun sensor(sensorId: SensorId, limit: Int): DatabaseGetSensorValuesResponse
}

typealias DatabaseGetValueResponse = Either<DatabaseValue, Exception>

typealias DatabaseGetAllValuesResponse = Either<List<DatabaseValue>, Exception>

typealias DatabaseGetSensorValuesResponse = Either<List<DatabaseValue>, Exception>

