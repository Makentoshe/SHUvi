package com.makentoshe.shuvi.cli

object EnvironmentOptions {

    val serverPortOption: String
        get() = "SHUVI_SERVER_PORT"

    val serverHostOption: String
        get() = "SHUVI_SERVER_HOST"

    val databaseHostOption: String
        get() = "SHUVI_DATABASE_HOST"

    val databasePortOption: String
        get() = "SHUVI_DATABASE_PORT"

    val databaseNameOption: String
        get() = "SHUVI_DATABASE_NAME"

    val databaseUserOption: String
        get() = "SHUVI_DATABASE_USER"

    val databasePasswordOption: String
        get() = "SHUVI_DATABASE_PASSWORD"
}