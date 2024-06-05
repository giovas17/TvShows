package com.example.tvshows.domain.di

import com.example.tvshows.domain.network.AppErrorHandling
import com.example.tvshows.domain.network.ErrorHandler
import com.example.tvshows.domain.network.TvShowService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.bind
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private const val TIMEOUT_IN_SECONDS = 30L
private const val BASE_URL = "https://api.tvmaze.com"

val networkModule = module {
    single { provideLoggerInterceptor() }
    single { provideOkHttpClient(get()) }
    single { provideRetrofit(get()) }
    single { provideService(get()) }
    single { AppErrorHandling(get()) } bind ErrorHandler::class
}

fun provideLoggerInterceptor() = HttpLoggingInterceptor().apply {
    level = HttpLoggingInterceptor.Level.BODY
}

fun provideOkHttpClient(interceptor: HttpLoggingInterceptor) =
    OkHttpClient.Builder()
        .connectTimeout(TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
        .readTimeout(TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
        .addInterceptor(interceptor)
        .build()

fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
    Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .build()

fun provideService(retrofit: Retrofit): TvShowService =
    retrofit.create(TvShowService::class.java)
