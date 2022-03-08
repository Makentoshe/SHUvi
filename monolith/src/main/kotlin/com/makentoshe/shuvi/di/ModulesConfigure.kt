package com.makentoshe.shuvi.di

import com.makentoshe.shuvi.cli.CliOptions
import com.makentoshe.shuvi.cli.EnvironmentOptions
import com.makentoshe.shuvi.cli.config.environment
import com.makentoshe.shuvi.cli.config.option
import com.makentoshe.shuvi.di.database.IdGeneratorConfig
import com.makentoshe.shuvi.di.database.PostgresDatabaseConfig
import com.makentoshe.shuvi.di.database.PostgresDatabaseModule
import com.makentoshe.shuvi.service.device.create.di.CreateDeviceServiceModule
import com.makentoshe.shuvi.service.device.delete.di.DeleteDeviceServiceModule
import com.makentoshe.shuvi.service.device.di.DeviceServiceModule
import com.makentoshe.shuvi.service.devices.di.DevicesServiceModule
import com.makentoshe.shuvi.service.sensor.create.di.CreateSensorServiceModule
import com.makentoshe.shuvi.service.sensor.di.GetSensorServiceModule
import org.apache.commons.cli.CommandLine
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin

fun configureModules(commandLine: CommandLine) = startKoin {
    configurePostgresDatabaseModule(commandLine)

    modules(
        DevicesServiceModule,
        CreateDeviceServiceModule,
        DeviceServiceModule,
        DeleteDeviceServiceModule,
        GetSensorServiceModule,
        CreateSensorServiceModule,
    )
}

private fun KoinApplication.configurePostgresDatabaseModule(commandLine: CommandLine) {
    val postgresDatabaseConfig = buildPostgresDatabaseConfig(commandLine)
    modules(PostgresDatabaseModule(IdGeneratorConfig(), postgresDatabaseConfig))
}

private fun buildPostgresDatabaseConfig(commandLine: CommandLine): PostgresDatabaseConfig {
    val databaseHost = commandLine.option(CliOptions.databaseHostOption) {
        environment(EnvironmentOptions.databaseHostOption, "localhost")
    }
    val databasePort = commandLine.option(CliOptions.databasePortOption) {
        environment(EnvironmentOptions.databasePortOption, "5432")
    }.toIntOrNull() ?: 5432 // additional check because environment or cli can give us a trash value
    val databaseName = commandLine.option(CliOptions.databaseNameOption) {
        environment(EnvironmentOptions.databaseNameOption, "developmentdb")
    }
    val databaseUser = commandLine.option(CliOptions.databaseUserOption) {
        environment(EnvironmentOptions.databaseUserOption, "developer")
    }
    val databasePassword = commandLine.option(CliOptions.databasePasswordOption) {
        environment(EnvironmentOptions.databasePasswordOption, "1243")
    }
    return PostgresDatabaseConfig(databaseHost, databasePort, databaseName, databaseUser, databasePassword)
}
