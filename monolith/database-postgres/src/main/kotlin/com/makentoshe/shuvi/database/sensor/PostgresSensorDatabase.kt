package com.makentoshe.shuvi.database.sensor

import com.makentoshe.shuvi.database.Postgres
import org.jetbrains.exposed.sql.Database

internal class PostgresSensorDatabase(override val database: Database) : Postgres(), SensorDatabase {
    override fun get() = PostgresGetSensorDatabase(database)

    override fun insert() = PostgresInsertSensorDatabase(database)
}
