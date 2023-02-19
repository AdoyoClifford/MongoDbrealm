package com.adoyo.di

import com.adoyo.model.Address
import com.adoyo.model.Person
import com.adoyo.model.Pet
import com.adoyo.data.MongoRepository
import com.adoyo.data.MongoRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module

object DataBaseModule {

    @Singleton
    @Provides
    fun provideRealm(): Realm {
        val config = RealmConfiguration.Builder(
            schema = setOf(Person::class, Address::class, Pet::class)
        ).compactOnLaunch()
            .build()
        return Realm.open(config)
    }


    @Singleton
    @Provides
    fun provideMongoRepository(realm: Realm): MongoRepository {
        return MongoRepositoryImpl(realm = realm)
    }
}