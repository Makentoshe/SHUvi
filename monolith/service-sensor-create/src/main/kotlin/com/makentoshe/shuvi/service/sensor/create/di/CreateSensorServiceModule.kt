package com.makentoshe.shuvi.service.sensor.create.di

import com.makentoshe.shuvi.repository.sensor.CreateSensorRepository
import com.makentoshe.shuvi.repository.sensor.create.CreateSensorRepositoryImpl
import com.makentoshe.shuvi.service.sensor.CreateSensorService
import com.makentoshe.shuvi.service.sensor.create.CreateSensorServiceImpl
import org.koin.dsl.module

val CreateSensorServiceModule = module {
    single<CreateSensorService> { CreateSensorServiceImpl(get()) }
    single<CreateSensorRepository> { CreateSensorRepositoryImpl(get(), get()) }
}