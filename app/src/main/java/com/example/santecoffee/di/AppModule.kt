package com.example.santecoffee.di

import android.content.Context
import androidx.room.Room
import com.example.santecoffee.db.FarmersDB
import com.example.santecoffee.util.FARMERS_DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providesRunningDatabase(@ApplicationContext applicationContext: Context)
            = Room.databaseBuilder(
        applicationContext,
        FarmersDB::class.java,
        FARMERS_DATABASE_NAME
    ).build()

    @Singleton
    @Provides
    fun providesRunningDao(database: FarmersDB) = database.getDao()
}