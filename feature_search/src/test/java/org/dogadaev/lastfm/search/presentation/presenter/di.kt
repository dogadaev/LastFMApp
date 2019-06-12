package org.dogadaev.lastfm.search.presentation.presenter

import io.mockk.mockk
import org.dogadaev.lastfm.search.data.repository.SearchRepository
import org.dogadaev.lastfm.search.presentation.contract.Search
import org.dogadaev.lastfm.search.presentation.presenter.data.FakeResourceProvider
import org.dogadaev.lastfm.search.presentation.presenter.data.FakeSearchRepository
import org.dogadaev.lastfm.statical.resources.ResourceProvider
import org.koin.dsl.module
import ru.terrakok.cicerone.Router

val searchTestModule = module {
    single<SearchRepository> { FakeSearchRepository() }
    single<ResourceProvider> { FakeResourceProvider() }
    single<Router> { mockk() }

    single<Search.Presenter> { SearchPresenter(get(), get(), get()) }
}