package com.makentoshe.shuvi.response.database

import com.makentoshe.shuvi.common.Either
import com.makentoshe.shuvi.entity.database.DatabaseInsertedSensor

typealias DatabaseInsertedSensorResponse = Either<DatabaseInsertedSensor, Exception>