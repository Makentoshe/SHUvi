package com.makentoshe.shuvi.database.value

import com.makentoshe.shuvi.common.Either
import com.makentoshe.shuvi.entity.database.value.DatabaseInsertedValue
import com.makentoshe.shuvi.entity.database.value.DatabaseValue

interface InsertValueDatabase {
    fun value(value: DatabaseValue): DatabaseInsertValueResponse
}

typealias DatabaseInsertValueResponse = Either<DatabaseInsertedValue, Exception>
