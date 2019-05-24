package org.dogadaev.lastfmapp

import androidx.appcompat.app.AppCompatDelegate
import androidx.multidex.MultiDexApplication
import com.arellomobile.mvp.RegisterMoxyReflectorPackages
import org.dogadaev.navigation.navigationModule
import org.dogadaev.net.networkModule
import org.dogadaev.statical.gson.gsonModule
import org.dogadaev.statical.resources.resourceProviderModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

@RegisterMoxyReflectorPackages()
class LastFMApplication : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        setupFrameworkConfigs()
        setupKoin()
    }

    private fun setupKoin() {
        startKoin {
            androidContext(this@LastFMApplication)
            modules(
                listOf(
                    navigationModule,
                    networkModule,
                    resourceProviderModule,
                    gsonModule
                )
            )
        }
    }

    private fun setupFrameworkConfigs() {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
    }
}