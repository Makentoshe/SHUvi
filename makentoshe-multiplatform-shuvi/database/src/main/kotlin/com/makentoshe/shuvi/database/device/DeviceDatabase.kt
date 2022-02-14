package com.makentoshe.shuvi.database.device

interface DeviceDatabase {
    fun getAll(): GetAllDeviceDatabase

    fun insert(): InsertDeviceDatabase
}

