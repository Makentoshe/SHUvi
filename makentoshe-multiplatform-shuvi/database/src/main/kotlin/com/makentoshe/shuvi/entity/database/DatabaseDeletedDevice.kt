package com.makentoshe.shuvi.entity.database

import com.makentoshe.shuvi.entity.device.DeletedDevice

/** Deleted device from the database */
data class DatabaseDeletedDevice(
    val databaseDevice: DatabaseDevice,
) {
    fun toDeletedDevice() = DeletedDevice(databaseDevice.toDevice())
}