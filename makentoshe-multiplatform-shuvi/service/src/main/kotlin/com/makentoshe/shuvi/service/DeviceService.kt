package com.makentoshe.shuvi.service

import com.makentoshe.shuvi.service.base.HtmlService
import com.makentoshe.shuvi.service.base.JsonService

/** Service that displays list of all known devices */
interface DeviceService : JsonService, HtmlService {

    override val routing: String
        get() = "/device{$formatParameter}"

    val formatParameter: String
        get() = "format"
}