package com.makentoshe.shuvi.entity

import com.makentoshe.shuvi.common.Either

typealias CreatedDeviceResponse = Either<CreatedDevice, Exception>

class CreatedDevice(val device: Device, val isSuccess: Boolean)
