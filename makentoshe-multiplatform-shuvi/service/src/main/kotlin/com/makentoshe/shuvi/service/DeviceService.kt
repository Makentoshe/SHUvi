package com.makentoshe.shuvi.service

/** Service that displays list of all known devices */
interface DeviceService : Service {
    override val routing: String
        get() = "/device.json"
}