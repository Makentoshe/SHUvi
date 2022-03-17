package com.makentoshe.shuvi.service.sensor.value.di

import com.makentoshe.shuvi.repository.sensor.value.SensorValueRepository
import com.makentoshe.shuvi.repository.sensor.value.SensorValueRepositoryImpl
import com.makentoshe.shuvi.service.sensor.ValueSensorService
import com.makentoshe.shuvi.service.sensor.value.ValueSensorServiceImpl
import org.koin.dsl.module

val ValueSensorServiceModule = module {
    single<SensorValueRepository> { SensorValueRepositoryImpl(get(), get()) }
    single<ValueSensorService> { ValueSensorServiceImpl(get()) }
}
