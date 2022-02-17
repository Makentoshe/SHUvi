package com.makentoshe.shuvi.service

interface DeleteDeviceService : Service {
    override val routing: String
        get() = "api/v1/device/delete"
}