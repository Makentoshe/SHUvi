package com.makentoshe.shuvi.service.device.create.di

import com.makentoshe.shuvi.repository.CreateDeviceRepository
import com.makentoshe.shuvi.repository.crossref.CreateSensorDeviceCrossrefRepository
import com.makentoshe.shuvi.repository.crossref.CreateSensorDeviceCrossrefRepositoryImpl
import com.makentoshe.shuvi.repository.device.create.CreateDeviceRepositoryImpl
import com.makentoshe.shuvi.service.CreateDeviceService
import com.makentoshe.shuvi.service.device.create.CreateDeviceServiceImpl
import org.koin.dsl.module

val CreateDeviceServiceModule = module {
    single<CreateSensorDeviceCrossrefRepository> { CreateSensorDeviceCrossrefRepositoryImpl(get()) }
    single<CreateDeviceRepository> { CreateDeviceRepositoryImpl(get(), get()) }
    single<CreateDeviceService> { CreateDeviceServiceImpl(get(), get(), get()) }
}