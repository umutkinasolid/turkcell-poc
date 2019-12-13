package com.solidict.poc.data_manager.di

import com.solidict.poc.api.Api
import com.solidict.poc.data_manager.network.interceptors.HeaderInterceptor
import com.solidict.poc.data_manager.network.util.LiveDataCallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

private const val TIMEOUT_MILLIS = "timeout_millis"
private const val TIMEOUT_UNIT = "timeout_unit"
private const val API_URL = "api_url"
private const val REST_URL = "http://34.245.145.145:8080/api/"

@Module
class NetworkModule {

    @Provides
    @Named(API_URL)
    fun provideUrl(): String = REST_URL

    @Provides
    @Named(TIMEOUT_MILLIS)
    fun provideTimeOutMillis() = 30000L

    @Provides
    @Named(TIMEOUT_UNIT)
    fun provideTimeOutUnit() = TimeUnit.MILLISECONDS


    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        return httpLoggingInterceptor.apply {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Provides
    @Singleton
    fun provideHttpClient(
        headerInterceptor: HeaderInterceptor,
        httpInterceptor: HttpLoggingInterceptor,
        @Named(TIMEOUT_MILLIS) timeOutMillis: Long,
        @Named(TIMEOUT_UNIT) timeOutUnit: TimeUnit
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(headerInterceptor)
            .addInterceptor(httpInterceptor)
            .readTimeout(timeOutMillis, timeOutUnit)
            .writeTimeout(timeOutMillis, timeOutUnit)
            .connectTimeout(timeOutMillis, timeOutUnit)
            .build()
    }

    @Provides
    @Singleton
    fun provideGsonConverterFactory(): Converter.Factory {
        return GsonConverterFactory.create()
    }

    @Provides
    @Singleton
    fun provideLiveDataCallAdapterFactory(liveDataCallAdapterFactory: LiveDataCallAdapterFactory): CallAdapter.Factory {
        return liveDataCallAdapterFactory
    }


    @Provides
    @Singleton
    fun provideRetrofit(
        @Named(API_URL) url: String,
        converterFactory: Converter.Factory,
        callAdapterFactory: CallAdapter.Factory,
        client: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(converterFactory)
            .addCallAdapterFactory(callAdapterFactory)
            .client(client)
            .build()
    }

    @Provides
    @Singleton
    fun provideApi(
        retrofit: Retrofit
    ): Api {
        return retrofit.create(Api::class.java)
    }


}