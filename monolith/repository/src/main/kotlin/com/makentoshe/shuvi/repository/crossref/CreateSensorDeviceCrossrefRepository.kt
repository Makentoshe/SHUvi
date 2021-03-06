package com.makentoshe.shuvi.repository.crossref

import com.makentoshe.shuvi.entity.DeviceId
import com.makentoshe.shuvi.entity.SensorId
import com.makentoshe.shuvi.response.repository.crossref.CreatedDeviceSensorCrossrefResponse

interface CreateSensorDeviceCrossrefRepository {

    /**
     * Creates a crossrefs between device and sensors, and return response with only created crossrefs.
     * That means, that only not existed crossrefs will be created and returned.
     * Existed crossrefs will be ignored.
     *
     * @param deviceId is a device id
     * @param sensors is a list of sensor ids which will be bound with [deviceId]
     * @return a successfully created crossrefs either exception.
     */
    fun createCrossrefs(deviceId: DeviceId, sensors: List<SensorId>): CreatedDeviceSensorCrossrefResponse
}