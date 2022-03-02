package com.makentoshe.shuvi.response.repository

import com.makentoshe.shuvi.common.Either
import com.makentoshe.shuvi.entity.device.DeletedDevice

/** Device deletion result from repository */
typealias DeletedDeviceResponse = Either<DeletedDevice, Exception>
