package com.rym.ialbum.di

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class iAlbumApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin{
           androidContext(applicationContext)
            modules(
                listOf(
                    networkModule,
                    repositoruModule,
                    usecaselModule,
                    viewmodelModule,
                    ioDispatcherModule

                )
            )
        }
    }
}