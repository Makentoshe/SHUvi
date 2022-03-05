package com.makentoshe.shuvi.database.crossref

import com.makentoshe.shuvi.database.Postgres

internal class PostgresCrossrefDatabase(
    override val database: org.jetbrains.exposed.sql.Database
) : Postgres(), CrossrefDatabase {
    override fun device2Sensors() = PostgresDeviceSensorsCrossrefDatabase(database)

}