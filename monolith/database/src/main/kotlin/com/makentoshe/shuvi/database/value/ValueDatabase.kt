package com.makentoshe.shuvi.database.value

interface ValueDatabase {

    fun insert(): InsertValueDatabase

    fun get(): GetValueDatabase
}
