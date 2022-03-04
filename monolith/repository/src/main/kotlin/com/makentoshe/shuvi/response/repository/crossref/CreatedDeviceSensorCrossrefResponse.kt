package com.makentoshe.shuvi.response.repository.crossref

import com.makentoshe.shuvi.common.Either
import com.makentoshe.shuvi.entity.repository.CreatedDeviceSensorCrossref

typealias CreatedDeviceSensorCrossrefResponse = Either<List<CreatedDeviceSensorCrossref>, Exception>
