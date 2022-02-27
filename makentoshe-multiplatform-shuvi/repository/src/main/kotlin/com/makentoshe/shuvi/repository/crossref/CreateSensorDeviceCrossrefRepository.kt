package com.makentoshe.shuvi.repository.crossref

import com.makentoshe.shuvi.entity.DeviceId
import com.makentoshe.shuvi.entity.SensorId
import com.makentoshe.shuvi.response.repository.crossref.CreatedDeviceSensorCrossrefResponse

interface CreateSensorDeviceCrossrefRepository {

    /** Creates a crossrefs between device and sensors, and return response with only created crossrefs */
    fun createCrossrefs(deviceId: DeviceId, sensors: List<SensorId>): CreatedDeviceSensorCrossrefResponse
}