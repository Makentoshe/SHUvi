package com.makentoshe.shuvi.cli

import org.apache.commons.cli.Option

object CliOptions {

    val serverPortOption: Option
        get() = Option("serverPort", true, "Server port")

    val serverHostOption: Option
        get() = Option("serverHost", true, "Server host")

    val databaseHostOption: Option
        get() = Option("databaseHost", true, "Database server host")

    val databasePortOption: Option
        get() = Option("databasePort", true, "Database server port")

    val databaseNameOption: Option
        get() = Option("databaseName", true, "Database name")

    val databaseUserOption: Option
        get() = Option("databaseUser", true, "Database username")

    val databasePasswordOption: Option
        get() = Option("databasePassword", true, "Database password for specified user")

    val list: List<Option>
        get() = listOf(
            serverPortOption,
            serverHostOption,
            databaseHostOption,
            databasePortOption,
            databaseNameOption,
            databaseUserOption,
            databasePasswordOption
        )
}
