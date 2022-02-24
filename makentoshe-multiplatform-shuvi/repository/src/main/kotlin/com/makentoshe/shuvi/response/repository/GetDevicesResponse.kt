package com.makentoshe.shuvi.response.repository

import com.makentoshe.shuvi.common.Either
import com.makentoshe.shuvi.entity.device.Device

typealias GetDevicesResponse = Either<List<Device>, Exception>
