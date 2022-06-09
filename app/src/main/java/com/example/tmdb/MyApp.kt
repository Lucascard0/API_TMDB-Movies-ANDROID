package com.example.tmdb

import android.app.Application
import com.example.tmdb.di.apiModule
import com.example.tmdb.di.retrofitModule
import com.example.tmdb.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApp : Application(){
    override fun onCreate() {
        super.onCreate()

        //Configuração obrigatória do Koin

        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApp)
            modules(
                retrofitModule, apiModule, viewModelModule
            )
        }


    }
}