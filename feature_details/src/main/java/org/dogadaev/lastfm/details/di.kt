package org.dogadaev.lastfm.details

import org.dogadaev.lastfm.details.data.repository.DetailsRepository
import org.dogadaev.lastfm.details.data.repository.DetailsRepositoryImpl
import org.dogadaev.lastfm.details.presentation.contract.Details
import org.dogadaev.lastfm.details.presentation.presenter.DetailsPresenter
import org.dogadaev.lastfm.details.presentation.view.activity.DetailsActivity
import org.dogadaev.lastfm.details.presentation.view.fragment.DetailsFragment
import org.dogadaev.lastfm.navigation.BaseDetailsScreen
import org.dogadaev.lastfm.navigation.DetailsScreen
import org.dogadaev.lastfm.net.API_KEY
import org.koin.dsl.module

val detailsModule = module {
    factory<Details.Presenter> { DetailsPresenter(get(), get(), get()) }

    factory<BaseDetailsScreen> { (artist: String, album: String) ->
        DetailsActivity.Screen(artist, album)
    }
    factory<DetailsScreen> { (artist: String, album: String) ->
        DetailsFragment.Screen(artist, album)
    }

    single<DetailsRepository> { DetailsRepositoryImpl(get(), get(API_KEY), get(), get()) }
}