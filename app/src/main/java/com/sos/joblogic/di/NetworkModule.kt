package com.sos.joblogic.di

import android.content.Context
import com.sos.joblogic.data.NoConnectionInterceptor
import com.sos.joblogic.data.api.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {


    @Singleton
    @Provides
    fun providesOkHttpClient(@ApplicationContext context: Context): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)

        return OkHttpClient.Builder().addInterceptor(logging)
            .addInterceptor(NoConnectionInterceptor(context)).connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS).build()

    }

    @Singleton
    @Provides
    fun providesRetrofit(okHttpClient: OkHttpClient): Retrofit {

        return Retrofit.Builder().baseUrl("https://my-json-server.typicode.com/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }
    @Singleton
    @Provides
    fun providesApiService(retrofit: Retrofit) : ApiService {
        return retrofit.create(ApiService::class.java)
    }


}