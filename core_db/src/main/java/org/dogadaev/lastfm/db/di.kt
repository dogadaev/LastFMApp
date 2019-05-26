package org.dogadaev.lastfm.db

import androidx.room.Room
import org.dogadaev.lastfm.db.database.AlbumsDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.core.qualifier.StringQualifier
import org.koin.dsl.module

val ALBUMS_DB_TAG = StringQualifier("org.dogadaev.lastfm.db.AlbumsDbTag")

val databaseModule = module {

    single {
        Room.databaseBuilder(androidApplication(), AlbumsDatabase::class.java, get(ALBUMS_DB_TAG))
            .build()
    }

    single { get<AlbumsDatabase>().albumsDao() }

    single(ALBUMS_DB_TAG) { "albums-db" }
}