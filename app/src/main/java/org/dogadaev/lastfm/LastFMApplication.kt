package org.dogadaev.lastfm

import androidx.appcompat.app.AppCompatDelegate
import androidx.multidex.MultiDexApplication
import com.arellomobile.mvp.RegisterMoxyReflectorPackages
import com.facebook.stetho.Stetho
import org.dogadaev.lastfm.albums.albumsModule
import org.dogadaev.lastfm.db.databaseModule
import org.dogadaev.lastfm.details.detailsModule
import org.dogadaev.lastfm.main.mainModule
import org.dogadaev.lastfm.navigation.navigationModule
import org.dogadaev.lastfm.net.networkModule
import org.dogadaev.lastfm.search.searchModule
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
    "org.dogadaev.lastfm.albums",
    "org.dogadaev.lastfm.details",
    "org.dogadaev.lastfm.main"
)
class LastFMApplication : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        setupFrameworkConfigs()
        setupStetho()
        setupKoin()
    }

    private fun setupKoin() {
        startKoin {
            androidContext(this@LastFMApplication)
            modules(
                listOf(
                    navigationModule,
                    networkModule,
                    databaseModule,
                    resourceProviderModule,
                    gsonModule,
                    mediaModule,
                    startModule,
                    searchModule,
                    albumsModule,
                    detailsModule,
                    mainModule
                )
            )
        }
    }

    private fun setupStetho() {
        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this)
        }
    }

    private fun setupFrameworkConfigs() {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
    }
}