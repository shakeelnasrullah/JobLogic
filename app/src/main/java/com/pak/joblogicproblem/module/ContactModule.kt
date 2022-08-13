package com.pak.joblogicproblem.module

import com.pak.joblogicproblem.repositories.ContactRepository
import com.pak.joblogicproblem.repositories.ContactRepositoryImpl
import com.pak.joblogicproblem.services.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@InstallIn(SingletonComponent::class)
@Module
class ContactModule {

    @Provides
    fun provideContactRepository(apiService: ApiService) : ContactRepository{
        return ContactRepositoryImpl(apiService)
    }
}