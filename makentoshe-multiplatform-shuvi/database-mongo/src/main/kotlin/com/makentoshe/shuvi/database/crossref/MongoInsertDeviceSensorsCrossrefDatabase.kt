package com.makentoshe.shuvi.database.crossref

import com.makentoshe.shuvi.common.Either
import com.makentoshe.shuvi.entity.database.crossref.DatabaseDeviceSensorsCrossref
import com.makentoshe.shuvi.entity.database.crossref.DatabaseInsertedDeviceSensorsCrossrefs
import com.mongodb.client.MongoCollection

internal class MongoInsertDeviceSensorsCrossrefDatabase(
    private val collection: MongoCollection<DatabaseDeviceSensorsCrossref>,
) : InsertDeviceSensorsCrossrefDatabase {
//    override fun insertCrossref(crossref: DatabaseDeviceSensorsCrossref) {
//        val insertOneResult = collection.insertOne(crossref)
//    }

    override fun insertCrossrefs(crossrefs: List<DatabaseDeviceSensorsCrossref>) = try {
        val insertManyResult = collection.insertMany(crossrefs)
        if (!insertManyResult.wasAcknowledged()) throw Exception("Insert $crossrefs wasn't acknowledged!")

        Either.Left(DatabaseInsertedDeviceSensorsCrossrefs(crossrefs))
    } catch (exception: Exception) {
        Either.Right(exception)
    }

}