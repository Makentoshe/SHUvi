package com.makentoshe.shuvi.database.device

import com.makentoshe.shuvi.database.Postgres

internal class PostgresDeviceDatabase(
    override val database: org.jetbrains.exposed.sql.Database,
) : Postgres(), DeviceDatabase {

    override fun get() = PostgresGetDevicesDatabase(database)

    override fun insert() = PostgresInsertDeviceDatabase(database)

    override fun delete() = PostgresDeleteDeviceDatabase(database)

}