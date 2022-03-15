package com.makentoshe.shuvi.database.value

import com.makentoshe.shuvi.entity.database.value.DatabaseValue
import com.makentoshe.shuvi.response.database.value.DatabaseInsertValueResponse

interface InsertValueDatabase {
    fun value(value: DatabaseValue): DatabaseInsertValueResponse
}