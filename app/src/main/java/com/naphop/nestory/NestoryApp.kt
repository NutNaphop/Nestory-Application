package com.naphop.nestory

import android.app.Application
import com.naphop.nestory.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class NestoryApp : Application(){
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@NestoryApp)
            modules(appModule)
        }
    }
}