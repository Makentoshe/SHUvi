package com.makentoshe.shuvi.service.device.di

import com.makentoshe.shuvi.repository.GetDeviceRepository
import com.makentoshe.shuvi.repository.device.GetDeviceRepositoryImpl
import com.makentoshe.shuvi.service.device.DeviceService
import com.makentoshe.shuvi.service.device.DeviceServiceImpl
import org.koin.dsl.module

val DeviceServiceModule = module {
    single<GetDeviceRepository> { GetDeviceRepositoryImpl(get()) }
    single<DeviceService> { DeviceServiceImpl(get()) }
}