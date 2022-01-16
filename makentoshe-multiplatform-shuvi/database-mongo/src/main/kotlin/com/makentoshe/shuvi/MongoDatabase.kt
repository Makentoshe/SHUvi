package com.makentoshe.shuvi

import org.litote.kmongo.KMongo
import org.litote.kmongo.getCollection

class MongoDatabase {

    val client = KMongo.createClient()
    val database = client.getDatabase("Database")
    val collection = database.getCollection<MongoDatabaseItem>()
}

data class MongoDatabaseItem(val id: String)