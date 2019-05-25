package org.dogadaev.lastfm.start

import org.dogadaev.lastfm.start.presentation.contract.Start
import org.dogadaev.lastfm.start.presentation.presenter.StartPresenter
import org.koin.dsl.module

val startModule = module {
    factory<Start.Presenter> { StartPresenter(get()) }
}