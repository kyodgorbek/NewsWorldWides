package com.example.newsworldwide

import android.app.Application

import com.example.newsworldwide.di.Modules.viewModels
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin


class NewsApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@NewsApplication)
            androidLogger()
            modules(viewModels)
        }
    }

}