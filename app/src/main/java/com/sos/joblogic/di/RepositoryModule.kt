package com.sos.joblogic.di

import com.sos.joblogic.data.api.ApiService
import com.sos.joblogic.data.local.RecordDao
import com.sos.joblogic.domain.repository.JobLogicRepositoryImpl
import com.sos.joblogic.domain.repository.RoomRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
class RepositoryModule {

        @Provides
        @Singleton
        fun provideJobLogicRepository(apiService: ApiService) = JobLogicRepositoryImpl(
                apiService = apiService, dispatcher = Dispatchers.IO
        )

        @Provides
        @Singleton
        fun provideRoomRepository(dao : RecordDao) = RoomRepositoryImpl(
                dao =  dao, dispatcher = Dispatchers.IO
        )
}