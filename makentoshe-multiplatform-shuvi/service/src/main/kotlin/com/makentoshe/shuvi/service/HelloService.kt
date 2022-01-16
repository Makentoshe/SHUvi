package com.makentoshe.shuvi.service

import com.makentoshe.shuvi.service.base.Service

interface HelloService: Service {
    fun sayHello() : String

    override val routing: String
        get() = "/hello"
}
