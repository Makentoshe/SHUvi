package com.makentoshe.shuvi.response.repository

import com.makentoshe.shuvi.common.Either
import com.makentoshe.shuvi.entity.device.CreatedDevice

typealias CreatedDeviceResponse = Either<CreatedDevice, Exception>
