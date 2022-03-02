package com.makentoshe.shuvi.database.crossref

import com.makentoshe.shuvi.entity.DeviceId
import com.makentoshe.shuvi.response.database.crossref.DatabaseGetDeviceSensorsCrossrefResponse

interface GetDeviceSensorsCrossrefDatabase {
    fun deviceId(deviceId: DeviceId): DatabaseGetDeviceSensorsCrossrefResponse
}