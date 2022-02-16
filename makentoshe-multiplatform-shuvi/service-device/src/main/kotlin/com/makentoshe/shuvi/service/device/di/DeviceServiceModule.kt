package com.makentoshe.shuvi.service.device.di

import com.makentoshe.shuvi.repository.DeviceRepository
import com.makentoshe.shuvi.repository.device.DeviceRepositoryImpl
import com.makentoshe.shuvi.service.DeviceService
import com.makentoshe.shuvi.service.device.DeviceServiceImpl
import org.koin.dsl.module

val DeviceServiceModule = module {
    single<DeviceRepository> { DeviceRepositoryImpl() }
    single<DeviceService> { DeviceServiceImpl(get()) }
}