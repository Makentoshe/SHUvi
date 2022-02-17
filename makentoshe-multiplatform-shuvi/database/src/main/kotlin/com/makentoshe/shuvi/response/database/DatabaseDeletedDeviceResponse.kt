package com.makentoshe.shuvi.response.database

import com.makentoshe.shuvi.common.Either
import com.makentoshe.shuvi.entity.database.DatabaseDeletedDevice

typealias DatabaseDeletedDeviceResponse = Either<DatabaseDeletedDevice, Exception>
