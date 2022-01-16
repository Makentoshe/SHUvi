package com.makentoshe.shuvi.service.device

import com.makentoshe.shuvi.repository.DeviceRepository
import com.makentoshe.shuvi.service.DeviceService
import kotlinx.html.*

class DeviceServiceImpl(private val repository: DeviceRepository) : DeviceService {

    override fun html(): (HTML.() -> Unit) = {
        val devices = repository.getDevices()
        val name = "Makentoshe"
        head {
            title {
                +name
            }
        }
        body {
            div { +"Devices count: ${devices.count()}" }
            div { +"Devices: $devices" }
        }
    }

    override fun json(): String {
        val devices = repository.getDevices()
        return "{ test: \"json\", devices: [${devices.joinToString(", ")}] }"
    }
}