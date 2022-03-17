package com.makentoshe.shuvi.database.value

import com.makentoshe.shuvi.database.Postgres
import org.jetbrains.exposed.sql.Database

class PostgresValueDatabase(override val database: Database) : Postgres(), ValueDatabase {
    override fun get(): GetValueDatabase = PostgressGetValueDatabase(database)

    override fun insert(): InsertValueDatabase = PostgresInsertValueDatabase(database)
}
