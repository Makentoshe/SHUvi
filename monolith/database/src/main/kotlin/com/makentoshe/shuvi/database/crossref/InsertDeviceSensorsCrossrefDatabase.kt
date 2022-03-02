package com.makentoshe.shuvi.database.crossref

import com.makentoshe.shuvi.entity.database.crossref.DatabaseDeviceSensorsCrossref
import com.makentoshe.shuvi.response.database.crossref.DatabaseInsertedDeviceSensorsCrossrefsResponse

interface InsertDeviceSensorsCrossrefDatabase {
//    fun insertCrossref(crossref: DatabaseDeviceSensorsCrossref)

    fun insertCrossrefs(crossrefs: List<DatabaseDeviceSensorsCrossref>): DatabaseInsertedDeviceSensorsCrossrefsResponse
}