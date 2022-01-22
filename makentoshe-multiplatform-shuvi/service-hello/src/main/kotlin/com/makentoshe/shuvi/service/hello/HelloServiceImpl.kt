package com.makentoshe.shuvi.service.hello

import com.makentoshe.shuvi.repository.HelloRepository
import com.makentoshe.shuvi.service.HelloService
import io.ktor.application.*
import io.ktor.response.*

class HelloServiceImpl(private val helloRepository: HelloRepository) : HelloService {
    override suspend fun handle(call: ApplicationCall) {
        call.respondText("Hello, ${helloRepository.getHello()}")
    }
}
