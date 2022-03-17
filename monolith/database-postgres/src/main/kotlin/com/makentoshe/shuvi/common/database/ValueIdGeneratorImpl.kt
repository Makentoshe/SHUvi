package com.makentoshe.shuvi.common.database

import com.makentoshe.shuvi.database.Database
import com.makentoshe.shuvi.entity.sensor.value.SensorValueId
import org.apache.commons.lang3.RandomStringUtils

class ValueIdGeneratorImpl(private val size: Int, private val database: Database) : ValueIdGenerator {
    override val generate: SensorValueId
        get() = SensorValueId(RandomStringUtils.randomAlphanumeric(size)).let { deviceId ->
            if (database.value().get().id(deviceId).isRight()) deviceId else generate
        }
}