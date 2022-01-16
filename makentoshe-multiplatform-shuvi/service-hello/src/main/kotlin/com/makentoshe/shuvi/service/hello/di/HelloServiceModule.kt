package com.makentoshe.shuvi.service.hello.di

import com.makentoshe.shuvi.service.HelloService
import com.makentoshe.shuvi.service.hello.repository.HelloRepository
import com.makentoshe.shuvi.service.hello.repository.HelloRepositoryImpl
import com.makentoshe.shuvi.service.hello.HelloServiceImpl
import org.koin.dsl.module

val HelloServiceModule = module {
    single<HelloService> { HelloServiceImpl(get()) } // get() Will resolve HelloRepository
    single<HelloRepository> { HelloRepositoryImpl() }
}