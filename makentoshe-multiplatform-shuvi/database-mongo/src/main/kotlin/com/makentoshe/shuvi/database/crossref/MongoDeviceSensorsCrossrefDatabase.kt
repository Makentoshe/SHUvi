package com.makentoshe.shuvi.database.crossref

import com.makentoshe.shuvi.entity.database.crossref.DatabaseDeviceSensorsCrossref
import com.mongodb.client.MongoCollection

internal class MongoDeviceSensorsCrossrefDatabase(
    private val collection: MongoCollection<DatabaseDeviceSensorsCrossref>,
) : DeviceSensorsCrossrefDatabase {

    override fun get(): GetDeviceSensorsCrossrefDatabase {
        return MongoGetDeviceSensorsCrossrefDatabase(collection)
    }

    override fun insert(): InsertDeviceSensorsCrossrefDatabase {
        return MongoInsertDeviceSensorsCrossrefDatabase(collection)
    }

    override fun exists(): ExistsDeviceSensorsCrossrefDatabase {
        return MongoExistsDeviceSensorsCrossrefDatabase(collection)
    }
}
