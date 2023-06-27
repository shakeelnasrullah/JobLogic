package com.sos.joblogic.di

import com.sos.joblogic.data.local.RecordDao
import com.sos.joblogic.domain.repository.JobLogicRepositoryImpl
import com.sos.joblogic.domain.repository.RoomRepositoryImpl
import com.sos.joblogic.domain.usecases.AddSellListUseCase
import com.sos.joblogic.domain.usecases.GetBuyListUseCase
import com.sos.joblogic.domain.usecases.GetCallListUseCase
import com.sos.joblogic.domain.usecases.GetSellListUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
class UseCaseModule {

    @Provides
    @Singleton
    fun provideGetBuyUseCase(repositoryImpl: JobLogicRepositoryImpl) = GetBuyListUseCase(
        repository = repositoryImpl, dispatcher = Dispatchers.IO )


    @Provides
    @Singleton
    fun provideGetSellUseCase(repository : RoomRepositoryImpl) = GetSellListUseCase(
        repository = repository, dispatcher =  Dispatchers.IO )


    @Provides
    @Singleton
    fun provideAddSellUseCase(repository: RoomRepositoryImpl) = AddSellListUseCase(
        repository =  repository, Dispatchers.IO )


    @Provides
    @Singleton
    fun provideGetCallUseCase(repository: JobLogicRepositoryImpl) = GetCallListUseCase(
        repository = repository, Dispatchers.IO
    )

}