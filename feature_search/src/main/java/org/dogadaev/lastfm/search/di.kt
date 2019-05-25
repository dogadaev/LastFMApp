package org.dogadaev.lastfm.search

import org.dogadaev.lastfm.search.presentation.contract.Search
import org.dogadaev.lastfm.search.presentation.presenter.SearchPresenter
import org.koin.dsl.module

val searchModule = module {
    factory<Search.Presenter> { SearchPresenter() }

}