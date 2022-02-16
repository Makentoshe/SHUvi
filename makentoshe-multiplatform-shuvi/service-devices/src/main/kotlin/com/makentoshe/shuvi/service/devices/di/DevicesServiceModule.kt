package com.makentoshe.shuvi.service.devices.di

import com.makentoshe.shuvi.repository.DeviceRepository
import com.makentoshe.shuvi.repository.devices.DeviceRepositoryImpl
import com.makentoshe.shuvi.service.DevicesService
import com.makentoshe.shuvi.service.devices.DevicesServiceImpl
import org.koin.dsl.module

val DevicesServiceModule = module {
    single<DevicesService> { DevicesServiceImpl(get()) }
    single<DeviceRepository> { DeviceRepositoryImpl(get()) }
}