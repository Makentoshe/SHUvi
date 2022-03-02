package com.makentoshe.shuvi.service

/** Service that displays list of all known devices */
interface DevicesService : Service {
    override val routing: String
        get() = "api/v1/devices.json"
}