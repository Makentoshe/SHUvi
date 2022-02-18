package com.makentoshe.shuvi.response.database

import com.makentoshe.shuvi.common.Either
import com.makentoshe.shuvi.entity.database.DatabaseSensor

typealias DatabaseGetSensorResponse = Either<DatabaseSensor, Exception>