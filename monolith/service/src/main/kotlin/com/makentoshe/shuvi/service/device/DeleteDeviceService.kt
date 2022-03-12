package com.makentoshe.shuvi.service.device

import com.makentoshe.shuvi.service.Service

interface DeleteDeviceService : Service {
    override val routing: String
        get() = "api/v1/device/delete"
}