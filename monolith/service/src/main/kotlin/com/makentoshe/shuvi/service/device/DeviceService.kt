package com.makentoshe.shuvi.service.device

import com.makentoshe.shuvi.service.Service


/** Service that returns a device by its id */
interface DeviceService : Service {
    override val routing: String
        get() = "api/v1/device/{$deviceIdParameter}.json"

    val deviceIdParameter: String
        get() = "deviceId"
}