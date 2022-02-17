package com.makentoshe.shuvi.database.device

/**
 * Database that may perform any actions with Device entities.
 */
interface DeviceDatabase {

    /** Request devices using selected filter */
    // TODO: implement filtering and paging
    fun get(): GetDevicesDatabase

    /** Request to insert a new device into the Device database */
    fun insert(): InsertDeviceDatabase

    /** Request to delete a device from the Device database */
    fun delete() : DeleteDeviceDatabase
}

