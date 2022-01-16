package com.makentoshe.shuvi.database.di

import com.makentoshe.shuvi.database.Database
import com.makentoshe.shuvi.database.MongoDatabase
import org.koin.dsl.module

val MongoDatabaseModule = module {
    single<Database> { MongoDatabase() }
}