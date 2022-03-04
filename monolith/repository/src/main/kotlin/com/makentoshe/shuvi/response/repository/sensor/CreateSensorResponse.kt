package com.makentoshe.shuvi.response.repository.sensor

import com.makentoshe.shuvi.common.Either
import com.makentoshe.shuvi.entity.CreatedSensor

typealias CreateSensorResponse = Either<CreatedSensor, Exception>