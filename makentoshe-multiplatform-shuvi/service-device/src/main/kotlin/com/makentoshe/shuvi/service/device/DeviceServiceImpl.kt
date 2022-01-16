package com.makentoshe.shuvi.service.device

import com.makentoshe.shuvi.service.DeviceService
import kotlinx.html.*

class DeviceServiceImpl : DeviceService {

    override fun html(): (HTML.() -> Unit) = {
        val name = "Makentoshe"
        head {
            title {
                +name
            }
        }
        body {
            h5 {
                +"Hello from $name!"
            }
        }
    }

    override fun json(): String {
        return "{ test: \"json\" }"
    }
}