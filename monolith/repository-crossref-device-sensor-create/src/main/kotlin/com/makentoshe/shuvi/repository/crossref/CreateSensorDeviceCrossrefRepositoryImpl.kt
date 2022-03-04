package com.makentoshe.shuvi.repository.crossref

import com.makentoshe.shuvi.common.Either
import com.makentoshe.shuvi.common.filterLeft2Right
import com.makentoshe.shuvi.common.flatMapLeft
import com.makentoshe.shuvi.common.flattenLeft
import com.makentoshe.shuvi.database.Database
import com.makentoshe.shuvi.entity.DeviceId
import com.makentoshe.shuvi.entity.SensorId
import com.makentoshe.shuvi.entity.database.crossref.DatabaseDeviceSensorsCrossref
import com.makentoshe.shuvi.entity.repository.CreatedDeviceSensorCrossref
import com.makentoshe.shuvi.response.repository.crossref.CreatedDeviceSensorCrossrefResponse

class CreateSensorDeviceCrossrefRepositoryImpl(
    private val database: Database,
) : CreateSensorDeviceCrossrefRepository {

    override fun createCrossrefs(deviceId: DeviceId, sensors: List<SensorId>): CreatedDeviceSensorCrossrefResponse {
        if (sensors.isEmpty()) return Either.Right(IllegalArgumentException("Empty sensors collection"))

        val crossrefs = sensors.map { sensorId -> DatabaseDeviceSensorsCrossref(deviceId.string, sensorId.string) }

        return crossrefs.map { crossref -> // check which crossref is exists
            database.crossref().device2Sensors().exists().crossref(crossref)
        }.flattenLeft().mapLeft { existsCrossrefs -> // we need only not existed crossrefs
            existsCrossrefs.filterNot { it.isExists }.map { it.crossref }
        }.flatMapLeft { unexistCrossrefs -> // insert unexisted crossrefs
            database.crossref().device2Sensors().insert().insertCrossrefs(unexistCrossrefs)
        }.mapLeft { insertedCrossref -> // map inserted crossref
            insertedCrossref.crossrefs.map { databaseCrossref ->
                CreatedDeviceSensorCrossref(DeviceId(databaseCrossref.deviceId), SensorId(databaseCrossref.sensorId))
            }
        }
    }
}