package com.makentoshe.shuvi.database.di

import com.makentoshe.shuvi.common.database.SensorIdGenerator
import com.makentoshe.shuvi.common.database.SensorIdGeneratorImpl
import com.makentoshe.shuvi.database.Database
import com.makentoshe.shuvi.database.MongoDatabase
import org.koin.dsl.module

val MongoDatabaseModule = module {
    single<Database> { MongoDatabase() }
    single<SensorIdGenerator> { SensorIdGeneratorImpl(size = 8, database = get()) }
}