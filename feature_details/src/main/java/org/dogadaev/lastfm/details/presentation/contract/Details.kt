package org.dogadaev.lastfm.details.presentation.contract

import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import org.dogadaev.lastfm.details.data.model.DetailsViewModel
import org.dogadaev.lastfm.statical.mvp.BasePresenter
import org.dogadaev.lastfm.statical.mvp.BaseView

interface Details {
    interface View : BaseView {
        @StateStrategyType(AddToEndSingleStrategy::class)
        fun onState(state: State)
    }

    abstract class Presenter : BasePresenter<View>() {
        abstract fun init(artist: String, album: String, mbid: String?)
    }

    sealed class State {
        class OnDisplay(val viewModel: DetailsViewModel) : State()
        object OnLoading : State()
        class OnError(val message: String) : State()
    }
}