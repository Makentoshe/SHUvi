package com.makentoshe.shuvi.database.crossref

import com.makentoshe.shuvi.database.Postgres

internal class PostgresDeviceSensorsCrossrefDatabase(
    override val database: org.jetbrains.exposed.sql.Database,
) : Postgres(), DeviceSensorsCrossrefDatabase {

    override fun get() = PostgresGetDeviceSensorsCrossrefDatabase(database)

    override fun insert() = PostgresInsertDeviceSensorsCrossrefDatabase(database)

    override fun exists() = PostgresExistsDeviceSensorsCrossrefDatabase(database)
}