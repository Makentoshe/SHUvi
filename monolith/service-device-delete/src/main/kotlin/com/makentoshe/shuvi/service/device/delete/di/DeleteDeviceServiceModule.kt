package com.makentoshe.shuvi.service.device.delete.di

import com.makentoshe.shuvi.repository.DeleteDeviceRepository
import com.makentoshe.shuvi.repository.device.delete.DeleteDeviceRepositoryImpl
import com.makentoshe.shuvi.service.device.DeleteDeviceService
import com.makentoshe.shuvi.service.device.delete.DeleteDeviceServiceImpl
import org.koin.dsl.module

val DeleteDeviceServiceModule = module {
    single<DeleteDeviceService> { DeleteDeviceServiceImpl(get()) }
    single<DeleteDeviceRepository> { DeleteDeviceRepositoryImpl(get()) }
}