package com.makentoshe.shuvi.service.device.delete.di

import com.makentoshe.shuvi.service.DeleteDeviceService
import com.makentoshe.shuvi.service.device.delete.DeleteDeviceServiceImpl
import org.koin.dsl.module

val DeleteDeviceServiceModule = module {
    single<DeleteDeviceService> { DeleteDeviceServiceImpl() }
}