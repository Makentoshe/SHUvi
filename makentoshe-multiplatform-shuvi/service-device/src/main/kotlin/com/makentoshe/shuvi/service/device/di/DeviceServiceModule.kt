package com.makentoshe.shuvi.service.device.di

import com.makentoshe.shuvi.service.DeviceService
import com.makentoshe.shuvi.service.device.DeviceServiceImpl
import org.koin.dsl.module

val DeviceServiceModule = module {
    single<DeviceService> { DeviceServiceImpl() }
}