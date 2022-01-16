package com.makentoshe.shuvi.service.device

import com.makentoshe.shuvi.repository.DeviceRepository
import com.makentoshe.shuvi.service.DeviceService
import kotlinx.html.*

class DeviceServiceImpl(private val repository: DeviceRepository) : DeviceService {

    override fun html(): (HTML.() -> Unit) = {
        val name = "Makentoshe"
        head {
            title {
                +name
            }
        }
        body {
            h5 {
                +"Devices: ${repository.getDevices()}!"
            }
        }
    }

    override fun json(): String {
        return "{ test: \"json\", devices: [${repository.getDevices().joinToString(", ")}] }"
    }
}