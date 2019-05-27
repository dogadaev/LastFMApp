package org.dogadaev.lastfm.main

import org.dogadaev.lastfm.main.data.repository.AlbumsRepository
import org.dogadaev.lastfm.main.data.repository.AlbumsRepositoryImpl
import org.dogadaev.lastfm.main.presentation.contract.Main
import org.dogadaev.lastfm.main.presentation.presenter.MainPresenter
import org.dogadaev.lastfm.main.presentation.view.activity.MainActivity
import org.dogadaev.lastfm.main.presentation.view.fragment.MainFragment
import org.dogadaev.lastfm.navigation.BaseMainScreen
import org.dogadaev.lastfm.navigation.MainScreen
import org.koin.dsl.module

val mainModule = module {
    factory<Main.Presenter> { MainPresenter(get(), get(), get()) }

    factory<BaseMainScreen> { MainActivity.Screen() }
    factory<MainScreen> { MainFragment.Screen() }

    single<AlbumsRepository> { AlbumsRepositoryImpl(get()) }
}