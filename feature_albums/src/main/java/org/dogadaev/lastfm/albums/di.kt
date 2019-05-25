package org.dogadaev.lastfm.albums

import org.dogadaev.lastfm.albums.data.repository.AlbumsRepository
import org.dogadaev.lastfm.albums.data.repository.AlbumsRepositoryImpl
import org.dogadaev.lastfm.albums.presentation.contract.Albums
import org.dogadaev.lastfm.albums.presentation.presenter.AlbumsPresenter
import org.dogadaev.lastfm.albums.presentation.view.activity.AlbumsActivity
import org.dogadaev.lastfm.albums.presentation.view.fragment.AlbumsFragment
import org.dogadaev.lastfm.navigation.AlbumsScreen
import org.dogadaev.lastfm.navigation.BaseAlbumsScreen
import org.dogadaev.lastfm.net.API_KEY
import org.koin.dsl.module

val albumsModule = module {
    factory<Albums.Presenter> { AlbumsPresenter(get()) }

    factory<BaseAlbumsScreen> { (artist: String, mbid: String?) -> AlbumsActivity.Screen(artist, mbid) }
    factory<AlbumsScreen> { (artist: String, mbid: String?) -> AlbumsFragment.Screen(artist, mbid) }

    single<AlbumsRepository> { AlbumsRepositoryImpl(get(), get(API_KEY)) }
}