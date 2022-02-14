package com.makentoshe.shuvi.service

interface CreateDeviceService: Service {
    override val routing: String
        get() = "api/v1/device/create"
}
