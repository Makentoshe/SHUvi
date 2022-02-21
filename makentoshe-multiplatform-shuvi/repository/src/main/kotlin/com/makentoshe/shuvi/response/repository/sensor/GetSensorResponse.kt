package com.makentoshe.shuvi.response.repository.sensor

import com.makentoshe.shuvi.common.Either
import com.makentoshe.shuvi.entity.Sensor

typealias GetSensorResponse = Either<Sensor, Exception>
