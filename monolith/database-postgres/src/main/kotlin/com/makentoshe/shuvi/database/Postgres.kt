package com.makentoshe.shuvi.database

import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.Transaction
import org.jetbrains.exposed.sql.addLogger

abstract class Postgres {
    internal abstract val database: org.jetbrains.exposed.sql.Database

    internal fun <T> transaction(f: Transaction.() -> T): T =
        org.jetbrains.exposed.sql.transactions.transaction(database) { addLogger(StdOutSqlLogger); f() }
}