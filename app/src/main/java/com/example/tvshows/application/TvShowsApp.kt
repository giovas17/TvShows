package com.example.tvshows.application

import android.app.Application
import com.example.tvshows.domain.di.networkModule
import com.example.tvshows.domain.di.tvShowDetailScreenModule
import com.example.tvshows.domain.di.tvShowScreenModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class TvShowsApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@TvShowsApp)
            modules(
                networkModule,
                tvShowScreenModule,
                tvShowDetailScreenModule,
            )
        }
    }
}