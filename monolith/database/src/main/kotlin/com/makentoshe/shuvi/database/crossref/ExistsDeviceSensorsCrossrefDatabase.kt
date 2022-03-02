package com.makentoshe.shuvi.database.crossref

import com.makentoshe.shuvi.entity.database.crossref.DatabaseDeviceSensorsCrossref
import com.makentoshe.shuvi.response.database.crossref.DatabaseExistsDeviceSensorsCrossrefResponse

interface ExistsDeviceSensorsCrossrefDatabase {
    fun crossref(crossref: DatabaseDeviceSensorsCrossref): DatabaseExistsDeviceSensorsCrossrefResponse
}