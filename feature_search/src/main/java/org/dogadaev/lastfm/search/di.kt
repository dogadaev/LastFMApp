package org.dogadaev.lastfm.search

import org.dogadaev.lastfm.navigation.BaseSearchScreen
import org.dogadaev.lastfm.navigation.SearchScreen
import org.dogadaev.lastfm.net.API_KEY
import org.dogadaev.lastfm.search.data.repository.SearchRepository
import org.dogadaev.lastfm.search.data.repository.SearchRepositoryImpl
import org.dogadaev.lastfm.search.presentation.contract.Search
import org.dogadaev.lastfm.search.presentation.presenter.SearchPresenter
import org.dogadaev.lastfm.search.presentation.view.activity.SearchActivity
import org.dogadaev.lastfm.search.presentation.view.fragment.SearchFragment
import org.koin.dsl.module

val searchModule = module {
    factory<BaseSearchScreen> { SearchActivity.Screen() }
    factory<SearchScreen> { SearchFragment.Screen() }

    factory<Search.Presenter> { SearchPresenter(get()) }

    single<SearchRepository> { SearchRepositoryImpl(get(), get(API_KEY)) }
}