package com.makentoshe.shuvi.database

import com.impossibl.postgres.jdbc.PGDataSource
import com.makentoshe.shuvi.database.crossref.CrossrefDatabase
import com.makentoshe.shuvi.database.crossref.PostgresCrossrefDatabase
import com.makentoshe.shuvi.database.device.DeviceDatabase
import com.makentoshe.shuvi.database.device.PostgresDeleteDeviceDatabase
import com.makentoshe.shuvi.database.device.PostgresDeviceDatabase
import com.makentoshe.shuvi.database.device.PostgresInsertDeviceDatabase
import com.makentoshe.shuvi.database.sensor.PostgresSensorDatabase
import com.makentoshe.shuvi.database.sensor.SensorDatabase
import com.makentoshe.shuvi.entity.DeviceId
import com.makentoshe.shuvi.entity.database.DatabaseDevice
import com.makentoshe.shuvi.entity.database.table.DatabaseDeviceTable
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

class PostgresDatabase(override val database: org.jetbrains.exposed.sql.Database) : Postgres(), Database {

    override fun device(): DeviceDatabase = PostgresDeviceDatabase(database)

    override fun sensor(): SensorDatabase = PostgresSensorDatabase(database)

    override fun crossref(): CrossrefDatabase = PostgresCrossrefDatabase(database)

}
