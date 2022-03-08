package com.makentoshe.shuvi.di.database

data class PostgresDatabaseConfig(
    val serverName: String,
    val serverPort: Int,
    val databaseName: String,
    val userName: String,
    val userPassword: String,
)