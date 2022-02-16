package com.makentoshe.shuvi.service.devices.di

import com.makentoshe.shuvi.repository.DevicesRepository
import com.makentoshe.shuvi.repository.devices.DevicesRepositoryImpl
import com.makentoshe.shuvi.service.DevicesService
import com.makentoshe.shuvi.service.devices.DevicesServiceImpl
import org.koin.dsl.module

val DevicesServiceModule = module {
    single<DevicesService> { DevicesServiceImpl(get()) }
    single<DevicesRepository> { DevicesRepositoryImpl(get()) }
}