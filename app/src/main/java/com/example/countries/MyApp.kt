package com.example.countries

import android.app.Application
import com.example.countries.koin.networkModule
import com.example.countries.koin.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin{
            androidContext(this@MyApp)
            modules(
                networkModule,
                viewModelModule
            )
        }
    }
}