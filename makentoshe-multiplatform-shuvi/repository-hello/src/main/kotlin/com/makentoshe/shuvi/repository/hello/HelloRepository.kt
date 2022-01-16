package com.makentoshe.shuvi.repository.hello

import com.makentoshe.shuvi.repository.HelloRepository

class HelloRepositoryImpl: HelloRepository {
    override fun getHello(): String {
        return "Makentoshe"
    }
}