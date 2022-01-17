package com.makentoshe.shuvi.service

interface CreateDeviceService: Service {
    override val routing: String
        get() = "/device/create.json"
}
