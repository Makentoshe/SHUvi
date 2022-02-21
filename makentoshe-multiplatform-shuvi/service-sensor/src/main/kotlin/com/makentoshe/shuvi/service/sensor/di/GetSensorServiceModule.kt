package com.makentoshe.shuvi.service.sensor.di

import com.makentoshe.shuvi.repository.sensor.GetSensorRepository
import com.makentoshe.shuvi.repository.sensor.get.GetSensorRepositoryImpl
import com.makentoshe.shuvi.service.sensor.GetSensorService
import com.makentoshe.shuvi.service.sensor.GetSensorServiceImpl
import org.koin.dsl.module

val GetSensorServiceModule = module {
    single<GetSensorService> { GetSensorServiceImpl(get()) }
    single<GetSensorRepository> { GetSensorRepositoryImpl(get()) }
}