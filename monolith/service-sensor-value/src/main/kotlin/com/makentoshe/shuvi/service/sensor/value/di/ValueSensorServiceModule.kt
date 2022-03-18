package com.makentoshe.shuvi.service.sensor.value.di

import com.makentoshe.shuvi.repository.sensor.value.ValueRepository
import com.makentoshe.shuvi.repository.sensor.value.ValueRepositoryImpl
import com.makentoshe.shuvi.service.sensor.ValueSensorService
import com.makentoshe.shuvi.service.sensor.value.ValueSensorServiceImpl
import org.koin.dsl.module

val ValueSensorServiceModule = module {
    single<ValueRepository> { ValueRepositoryImpl(get(), get()) }
    single<ValueSensorService> { ValueSensorServiceImpl(get()) }
}
