package org.dogadaev.lastfm.albums

import org.dogadaev.lastfm.albums.presentation.contract.Albums
import org.dogadaev.lastfm.albums.presentation.presenter.AlbumsPresenter
import org.dogadaev.lastfm.albums.presentation.view.activity.AlbumsActivity
import org.dogadaev.lastfm.albums.presentation.view.fragment.AlbumsFragment
import org.dogadaev.lastfm.navigation.AlbumsScreen
import org.dogadaev.lastfm.navigation.BaseAlbumsScreen
import org.koin.dsl.module

val albumsModule = module {
    factory<Albums.Presenter> { AlbumsPresenter() }

    factory<BaseAlbumsScreen> { (artist: String) -> AlbumsActivity.Screen(artist) }
    factory<AlbumsScreen> { (artist: String) -> AlbumsFragment.Screen(artist) }
}