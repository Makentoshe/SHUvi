package com.makentoshe.shuvi.entity.repository

import com.makentoshe.shuvi.entity.DeviceId
import com.makentoshe.shuvi.entity.SensorId

/** Created crossref between device and sensor */
class CreatedDeviceSensorCrossref(val deviceId: DeviceId, val sensorId: SensorId)