package org.dogadaev.lastfm.app

import android.app.Application
import org.dogadaev.lastfm.navigation.navigationModule
import org.dogadaev.lastfm.net.networkModule
import org.dogadaev.lastfm.search.searchModule
import org.dogadaev.lastfm.statical.media.mediaModule
import org.dogadaev.lastfm.statical.resources.resourceProviderModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class TestApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@TestApp)
            modules(
                listOf(
                    mediaModule,
                    navigationModule,
                    searchModule,
                    resourceProviderModule,
                    networkModule
                )
            )
        }
    }
}