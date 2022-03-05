package com.makentoshe.shuvi.di.database

import com.impossibl.postgres.jdbc.PGDataSource
import com.makentoshe.shuvi.common.database.DeviceIdGenerator
import com.makentoshe.shuvi.common.database.DeviceIdGeneratorImpl
import com.makentoshe.shuvi.common.database.SensorIdGenerator
import com.makentoshe.shuvi.common.database.SensorIdGeneratorImpl
import com.makentoshe.shuvi.database.Database
import com.makentoshe.shuvi.database.PostgresDatabase
import javax.sql.DataSource
import org.koin.dsl.module

val PostgresDatabaseModule = module {
    single<Database> { PostgresDatabase(org.jetbrains.exposed.sql.Database.connect(datasource())) }
    single<SensorIdGenerator> { SensorIdGeneratorImpl(size = 8, database = get()) }
    single<DeviceIdGenerator> { DeviceIdGeneratorImpl(size = 8, database = get()) }
}

private fun datasource(): DataSource = PGDataSource().apply {
    serverName = "localhost"
    port = 5432
    databaseName = "developmentdp"
    user = "developer"
    password = "1243"
}