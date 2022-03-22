package com.makentoshe.shuvi.service.value.get.di

import com.makentoshe.shuvi.repository.sensor.value.ValueRepository
import com.makentoshe.shuvi.repository.sensor.value.ValueRepositoryImpl
import com.makentoshe.shuvi.service.value.GetValuesService
import com.makentoshe.shuvi.service.value.get.GetValuesServiceImpl
import org.koin.dsl.module

val ValuesServiceModule = module {
    single<ValueRepository> { ValueRepositoryImpl(get(), get()) }

    single<GetValuesService> { GetValuesServiceImpl(get()) }
}
