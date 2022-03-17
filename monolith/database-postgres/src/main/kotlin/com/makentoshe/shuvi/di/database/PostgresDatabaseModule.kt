package com.makentoshe.shuvi.di.database

import com.impossibl.postgres.jdbc.PGDataSource
import com.makentoshe.shuvi.common.database.DeviceIdGenerator
import com.makentoshe.shuvi.common.database.DeviceIdGeneratorImpl
import com.makentoshe.shuvi.common.database.SensorIdGenerator
import com.makentoshe.shuvi.common.database.SensorIdGeneratorImpl
import com.makentoshe.shuvi.common.database.ValueIdGenerator
import com.makentoshe.shuvi.common.database.ValueIdGeneratorImpl
import com.makentoshe.shuvi.database.Database
import com.makentoshe.shuvi.database.PostgresDatabase
import javax.sql.DataSource
import org.koin.dsl.module

fun PostgresDatabaseModule(idGeneratorConfig: IdGeneratorConfig, postgresConfig: PostgresDatabaseConfig) = module {
    single<Database> { PostgresDatabase(org.jetbrains.exposed.sql.Database.connect(datasource(postgresConfig))) }
    single<SensorIdGenerator> { SensorIdGeneratorImpl(size = idGeneratorConfig.sensorIdSize, database = get()) }
    single<DeviceIdGenerator> { DeviceIdGeneratorImpl(size = idGeneratorConfig.deviceIdSize, database = get()) }
    single<ValueIdGenerator> { ValueIdGeneratorImpl(size = idGeneratorConfig.valueIdSize, database = get()) }
}

private fun datasource(config: PostgresDatabaseConfig): DataSource = PGDataSource().apply {
    serverName = config.serverName
    port = config.serverPort
    databaseName = config.databaseName
    user = config.userName
    password = config.userPassword
}