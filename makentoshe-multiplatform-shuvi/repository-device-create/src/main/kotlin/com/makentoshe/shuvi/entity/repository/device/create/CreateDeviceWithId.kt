package com.makentoshe.shuvi.entity.repository.device.create

import com.makentoshe.shuvi.entity.CreateDevice
import com.makentoshe.shuvi.entity.Device
import com.makentoshe.shuvi.entity.DeviceId

internal typealias CreateDeviceWithId = Device

internal fun CreateDevice.toCreateDeviceWithId(deviceId: DeviceId): CreateDeviceWithId {
    return Device(deviceId, title, sensors)
}
