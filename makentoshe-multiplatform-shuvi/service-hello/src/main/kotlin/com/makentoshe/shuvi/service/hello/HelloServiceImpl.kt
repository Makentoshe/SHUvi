package com.makentoshe.shuvi.service.hello

import com.makentoshe.shuvi.service.HelloService
import com.makentoshe.shuvi.service.hello.repository.HelloRepository

class HelloServiceImpl(private val helloRepository: HelloRepository) : HelloService {
    override fun sayHello(): String {
        return "Hello, ${helloRepository.getHello()}"
    }
}
