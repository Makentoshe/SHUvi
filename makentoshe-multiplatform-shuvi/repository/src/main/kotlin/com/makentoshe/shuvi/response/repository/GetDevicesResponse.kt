package com.makentoshe.shuvi.response.repository

import com.makentoshe.shuvi.common.Either
import com.makentoshe.shuvi.entity.Device

typealias GetDevicesResponse = Either<List<Device>, Exception>
