package org.dogadaev.lastfm

import androidx.appcompat.app.AppCompatDelegate
import androidx.multidex.MultiDexApplication
import com.arellomobile.mvp.RegisterMoxyReflectorPackages
import org.dogadaev.lastfm.albums.albumsModule
import org.dogadaev.lastfm.search.searchModule
import org.dogadaev.lastfm.navigation.navigationModule
import org.dogadaev.lastfm.net.networkModule
import org.dogadaev.lastfm.start.startModule
import org.dogadaev.lastfm.statical.gson.gsonModule
import org.dogadaev.lastfm.statical.media.mediaModule
import org.dogadaev.lastfm.statical.resources.resourceProviderModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

@Suppress("unused")
@RegisterMoxyReflectorPackages(
    "org.dogadaev.lastfm.start",
    "org.dogadaev.lastfm.search",
    "org.dogadaev.lastfm.albums"
)
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
                    gsonModule,
                    mediaModule,
                    startModule,
                    searchModule,
                    albumsModule
                )
            )
        }
    }

    private fun setupFrameworkConfigs() {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
    }
}