package com.br.swile.tech.core.network

import com.br.swile.tech.BuildConfig
import com.br.swile.tech.transaction.remote.TransactionModule
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Singleton

@Module(includes = [TransactionModule::class])
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun providesIoDispatcher(): CoroutineDispatcher = Dispatchers.IO

    @Provides
    @Singleton
    fun providesJson(): Json = Json { ignoreUnknownKeys = true }

    @Provides
    @Singleton
    fun providesRetrofit(
        jsonProperties: Json
    ): Retrofit = Retrofit.Builder()
        .baseUrl("https://gist.githubusercontent.com/")
        .client(
            OkHttpClient.Builder().addInterceptor(
                HttpLoggingInterceptor().apply {
                    if (BuildConfig.DEBUG) {
                        setLevel(HttpLoggingInterceptor.Level.BODY)
                    }
                },
            ).build()
        )
        .addConverterFactory(
            @OptIn(ExperimentalSerializationApi::class)
            jsonProperties.asConverterFactory("application/json".toMediaType()),
        )
        .build()
}
