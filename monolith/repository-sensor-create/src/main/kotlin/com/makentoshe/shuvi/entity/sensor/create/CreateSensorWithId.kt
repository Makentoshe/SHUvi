package com.makentoshe.shuvi.entity.sensor.create

import com.makentoshe.shuvi.entity.*

internal typealias CreateSensorWithId = Sensor

internal fun CreateSensor.toCreateSensorWithId(sensorId: SensorId): CreateSensorWithId {
    return Sensor(sensorId, title)
}
