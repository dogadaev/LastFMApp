package org.dogadaev.lastfm.search.presentation.contract

import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import org.dogadaev.lastfm.statical.mvp.BasePresenter
import org.dogadaev.lastfm.statical.mvp.BaseView

interface Search {
    interface View : BaseView {
        @StateStrategyType(AddToEndSingleStrategy::class)
        fun onState(state: State)
    }

    abstract class Presenter : BasePresenter<View>()

    sealed class State
}