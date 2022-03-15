package com.makentoshe.shuvi.database.value

import com.makentoshe.shuvi.entity.sensor.value.SensorValueId
import com.makentoshe.shuvi.response.database.value.DatabaseGetValueResponse

interface GetValueDatabase {
    fun id(value: SensorValueId): DatabaseGetValueResponse
}