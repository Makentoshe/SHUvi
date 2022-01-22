package com.makentoshe.shuvi.service

interface HelloService: Service {
    override val routing: String
        get() = "/hello"
}
