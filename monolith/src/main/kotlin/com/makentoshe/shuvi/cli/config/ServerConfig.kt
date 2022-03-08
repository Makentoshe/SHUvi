package com.makentoshe.shuvi.cli.config

import com.makentoshe.shuvi.cli.CliOptions
import com.makentoshe.shuvi.cli.EnvironmentOptions
import org.apache.commons.cli.CommandLine
import org.apache.commons.cli.Option

data class ServerConfig(
    val serverPort: Int,
    val serverHost: String
) {
    constructor(commandLine: CommandLine) : this(
        serverPort = commandLine.option(CliOptions.serverPortOption) {
            environment(EnvironmentOptions.serverPortOption, default = "8080")
        }.toInt(),

        serverHost = commandLine.option(CliOptions.serverHostOption) {
            environment(EnvironmentOptions.serverHostOption, default = "127.0.0.1")
        }
    )
}
