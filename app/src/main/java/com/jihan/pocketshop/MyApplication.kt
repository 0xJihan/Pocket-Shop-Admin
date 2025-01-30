package com.jihan.pocketshop

import android.app.Application
import com.jihan.pocketshop.di.appModule
import com.michaelflisar.composethemer.ComposeTheme
import com.michaelflisar.composethemer.themes.ComposeThemes
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApplication)
            modules(appModule)
        }

        ComposeTheme.register(*ComposeThemes.ALL.toTypedArray())

    }

}