package com.mona.codetest_boost

import android.app.Application
import com.mona.codetest_boost.di.apiModule
import com.mona.codetest_boost.di.networkModule
import com.mona.codetest_boost.di.repositoryModule
import com.mona.codetest_boost.di.viewModelsModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(
                apiModule,
                viewModelsModule,
                repositoryModule,
                networkModule,
            )
        }
    }
}