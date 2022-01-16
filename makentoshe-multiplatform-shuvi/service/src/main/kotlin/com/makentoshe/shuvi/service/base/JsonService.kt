package com.makentoshe.shuvi.service.base

/** Service that returns a json as a response */
interface JsonService: Service {
    fun json(): String
}