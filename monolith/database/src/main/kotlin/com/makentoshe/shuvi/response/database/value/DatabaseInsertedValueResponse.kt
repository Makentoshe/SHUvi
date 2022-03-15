package com.makentoshe.shuvi.response.database.value

import com.makentoshe.shuvi.common.Either
import com.makentoshe.shuvi.entity.database.value.DatabaseInsertedValue

typealias DatabaseInsertValueResponse = Either<DatabaseInsertedValue, Exception>
