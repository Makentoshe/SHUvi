package com.makentoshe.shuvi.database.device

import com.makentoshe.shuvi.response.database.DatabaseGetDevicesResponse

interface GetDevicesDatabase {
    fun getDevices(): DatabaseGetDevicesResponse
}