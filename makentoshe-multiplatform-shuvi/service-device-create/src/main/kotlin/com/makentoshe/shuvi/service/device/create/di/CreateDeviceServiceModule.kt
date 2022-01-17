package com.makentoshe.shuvi.service.device.create.di

import com.makentoshe.shuvi.service.CreateDeviceService
import com.makentoshe.shuvi.service.device.create.CreateDeviceServiceImpl
import org.koin.dsl.module

val CreateDeviceServiceModule = module {
    single<CreateDeviceService> { CreateDeviceServiceImpl() }
}