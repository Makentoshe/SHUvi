package com.makentoshe.shuvi.entity.database

import com.makentoshe.shuvi.entity.DeletedDevice

/** Deleted device from the database */
data class DatabaseDeletedDevice(
    val databaseDevice: DatabaseDevice,
) {
    fun toDeletedDevice() = DeletedDevice(databaseDevice.toDevice(emptyList()))
}