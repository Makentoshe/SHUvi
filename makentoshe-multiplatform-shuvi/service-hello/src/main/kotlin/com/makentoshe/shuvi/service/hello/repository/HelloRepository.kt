package com.makentoshe.shuvi.service.hello.repository

interface HelloRepository {
    fun getHello(): String
}

class HelloRepositoryImpl: HelloRepository {
    override fun getHello(): String {
        return "Makentoshe"
    }
}