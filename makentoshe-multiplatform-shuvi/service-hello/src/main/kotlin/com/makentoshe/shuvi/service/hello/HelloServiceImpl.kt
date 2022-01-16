package com.makentoshe.shuvi.service.hello

import com.makentoshe.shuvi.repository.HelloRepository
import com.makentoshe.shuvi.service.HelloService

class HelloServiceImpl(private val helloRepository: HelloRepository) : HelloService {
    override fun sayHello(): String {
        return "Hello, ${helloRepository.getHello()}"
    }
}
