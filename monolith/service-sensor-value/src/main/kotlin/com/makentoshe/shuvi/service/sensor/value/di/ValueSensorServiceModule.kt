package com.makentoshe.shuvi.service.sensor.value.di

import com.makentoshe.shuvi.service.sensor.ValueSensorService
import com.makentoshe.shuvi.service.sensor.value.ValueSensorServiceImpl
import org.koin.dsl.module

val ValueSensorServiceModule = module {
    single<ValueSensorService> { ValueSensorServiceImpl() }
}
