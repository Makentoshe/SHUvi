package com.makentoshe.shuvi.response.database.crossref

import com.makentoshe.shuvi.common.Either
import com.makentoshe.shuvi.entity.database.crossref.DatabaseDeviceSensorsCrossref

typealias DatabaseGetDeviceSensorsCrossrefResponse = Either<List<DatabaseDeviceSensorsCrossref>, Exception>