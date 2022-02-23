package com.makentoshe.shuvi.common.database

import com.makentoshe.shuvi.database.Database
import com.makentoshe.shuvi.entity.DeviceId
import org.apache.commons.lang3.RandomStringUtils

/** Generates a [size] bytes device id. It will retry recursively till unique id will be created */
class DeviceIdGeneratorImpl(private val size: Int, private val database: Database) : DeviceIdGenerator {

    override val generate: DeviceId
        get() = DeviceId(RandomStringUtils.randomAlphanumeric(size)).let { deviceId ->
            if (database.device().get().id(deviceId).isRight()) deviceId else generate
        }
}