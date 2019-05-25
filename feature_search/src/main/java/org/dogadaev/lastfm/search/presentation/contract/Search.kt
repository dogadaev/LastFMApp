package org.dogadaev.lastfm.search.presentation.contract

import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import org.dogadaev.lastfm.search.data.model.SearchViewModel
import org.dogadaev.lastfm.statical.mvp.BasePresenter
import org.dogadaev.lastfm.statical.mvp.BaseView

interface Search {
    interface View : BaseView {
        @StateStrategyType(AddToEndSingleStrategy::class)
        fun onState(state: State)
    }

    abstract class Presenter : BasePresenter<View>() {
        abstract fun performNewSearch(searchQuery: String)
        abstract fun loadNextPage()
    }

    sealed class State {
        class OnDisplay(val viewModel: SearchViewModel) : State()
        object OnLoading : State()
        class OnError(val message: String) : State()
    }
}