package com.makentoshe.shuvi.response.database.value

import com.makentoshe.shuvi.common.Either
import com.makentoshe.shuvi.entity.database.value.DatabaseValue

typealias DatabaseGetValueResponse = Either<DatabaseValue, Exception>
