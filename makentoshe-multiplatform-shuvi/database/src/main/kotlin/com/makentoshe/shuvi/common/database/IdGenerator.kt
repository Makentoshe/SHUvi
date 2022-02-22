package com.makentoshe.shuvi.common.database

interface IdGenerator<T: Any> {
    val generate: T
}