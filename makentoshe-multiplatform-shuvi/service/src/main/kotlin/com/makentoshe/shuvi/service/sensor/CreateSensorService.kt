package com.makentoshe.shuvi.service.sensor

import com.makentoshe.shuvi.service.Service

interface CreateSensorService : Service {

    override val routing: String
        get() = "api/v1/sensor/create"
}