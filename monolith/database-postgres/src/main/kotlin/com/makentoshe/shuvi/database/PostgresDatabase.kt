package com.makentoshe.shuvi.database

import com.makentoshe.shuvi.database.crossref.CrossrefDatabase
import com.makentoshe.shuvi.database.crossref.PostgresCrossrefDatabase
import com.makentoshe.shuvi.database.device.DeviceDatabase
import com.makentoshe.shuvi.database.device.PostgresDeviceDatabase
import com.makentoshe.shuvi.database.sensor.PostgresSensorDatabase
import com.makentoshe.shuvi.database.sensor.SensorDatabase
import com.makentoshe.shuvi.database.value.PostgresValueDatabase
import com.makentoshe.shuvi.database.value.ValueDatabase

class PostgresDatabase(override val database: org.jetbrains.exposed.sql.Database) : Postgres(), Database {

    override fun device(): DeviceDatabase = PostgresDeviceDatabase(database)

    override fun sensor(): SensorDatabase = PostgresSensorDatabase(database)

    override fun crossref(): CrossrefDatabase = PostgresCrossrefDatabase(database)

    override fun value(): ValueDatabase = PostgresValueDatabase(database)
}
