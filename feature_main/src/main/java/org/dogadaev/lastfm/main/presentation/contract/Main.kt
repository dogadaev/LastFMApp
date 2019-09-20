package org.dogadaev.lastfm.main.presentation.contract

import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import org.dogadaev.lastfm.main.data.model.MainViewModel
import org.dogadaev.lastfm.statical.mvp.BasePresenter_LEGACY
import org.dogadaev.lastfm.statical.mvp.BaseView

interface Main {
    interface View : BaseView {
        @StateStrategyType(AddToEndSingleStrategy::class)
        fun onState(state: State)
    }

    abstract class Presenter : BasePresenter_LEGACY<View>() {
        abstract fun refresh()
        abstract fun openSearch()
        abstract fun openDetails(position: Int)
    }

    sealed class State {
        class OnDisplay(val viewModel: MainViewModel) : State()
        object OnLoading : State()
        class OnError(val message: String) : State()
    }
}