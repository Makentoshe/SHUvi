package com.makentoshe.shuvi.entity.database

import com.makentoshe.shuvi.entity.DeviceId

@JvmInline
value class DatabaseDeviceId(val string: String) {
    fun toDeviceId() = DeviceId(string)
}