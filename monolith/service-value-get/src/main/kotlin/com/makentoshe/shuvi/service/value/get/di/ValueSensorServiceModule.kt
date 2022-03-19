package com.makentoshe.shuvi.service.value.get.di

import com.makentoshe.shuvi.repository.sensor.value.ValueRepository
import com.makentoshe.shuvi.repository.sensor.value.ValueRepositoryImpl
import com.makentoshe.shuvi.service.value.GetValueService
import com.makentoshe.shuvi.service.value.get.GetValueServiceImpl
import org.koin.dsl.module

val GetValueServiceModule = module {
    single<ValueRepository> { ValueRepositoryImpl(get(), get()) }
    single<GetValueService> { GetValueServiceImpl(get()) }
}
