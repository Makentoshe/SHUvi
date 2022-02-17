package com.makentoshe.shuvi.response.repository

import com.makentoshe.shuvi.common.Either
import com.makentoshe.shuvi.entity.CreatedDevice

typealias CreatedDeviceResponse = Either<CreatedDevice, Exception>
