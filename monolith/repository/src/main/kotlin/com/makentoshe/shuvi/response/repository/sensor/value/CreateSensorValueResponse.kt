package com.makentoshe.shuvi.response.repository.sensor.value

import com.makentoshe.shuvi.common.Either
import com.makentoshe.shuvi.entity.CreatedSensor
import com.makentoshe.shuvi.entity.sensor.value.CreatedSensorValue

typealias CreateSensorValueResponse = Either<CreatedSensorValue, Exception>