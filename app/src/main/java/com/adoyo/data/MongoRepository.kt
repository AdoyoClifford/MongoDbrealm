package com.adoyo.data

import com.adoyo.model.Person
import kotlinx.coroutines.flow.Flow
import org.mongodb.kbson.ObjectId

interface MongoRepository {
    fun getData(): Flow<List<Person>>
    fun filterData(name: String): Flow<List<Person>>
    suspend fun insertPerson(person: Person)
    suspend fun updatePerson(person: Person)
    suspend fun deletePerson(id: ObjectId)

}