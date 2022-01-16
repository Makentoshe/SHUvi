package com.makentoshe.shuvi.service

interface HelloService: Service {
    fun sayHello() : String

    override val routing: String
        get() = "/hello"
}
