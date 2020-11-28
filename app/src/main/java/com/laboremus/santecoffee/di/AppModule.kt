package com.laboremus.santecoffee.di

import android.content.Context
import androidx.room.Room
import com.laboremus.santecoffee.adapter.FarmerAdapter
import com.laboremus.santecoffee.db.FarmersDB
import com.laboremus.santecoffee.services.RequestManager
import com.laboremus.santecoffee.util.FARMERS_DATABASE_NAME
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

    @Singleton
    @Provides
    fun providesRequestManager()
            = RequestManager()

    @Singleton
    @Provides
    fun provideContext(@ApplicationContext applicationContext: Context) =
        applicationContext

    @Singleton
    @Provides
    fun provideAdapter(context: Context) = FarmerAdapter(context)
}