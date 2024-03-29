package com.sos.joblogic.di

import android.content.Context
import androidx.room.Room
import com.sos.joblogic.data.local.JobLogicDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(
        context = context.applicationContext,
        klass = JobLogicDB::class.java, name = "jobLogicDB"
    ).build()

    @Provides
    @Singleton
    fun providesRecordDao(database: JobLogicDB) = database.getRecordDao()

}