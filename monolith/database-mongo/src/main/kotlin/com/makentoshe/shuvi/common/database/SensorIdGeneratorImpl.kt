package com.makentoshe.shuvi.common.database

import com.makentoshe.shuvi.database.Database
import com.makentoshe.shuvi.entity.SensorId
import org.apache.commons.lang3.RandomStringUtils

/** Generates a [size] bytes sensor id. It will retry recursively till unique id will be created */
class SensorIdGeneratorImpl(private val size: Int, private val database: Database) : SensorIdGenerator {

    override val generate: SensorId
        get() = SensorId(RandomStringUtils.randomAlphanumeric(size)).let { sensorId ->
            if (database.sensor().get().id(sensorId).isRight()) sensorId else generate
        }
}

