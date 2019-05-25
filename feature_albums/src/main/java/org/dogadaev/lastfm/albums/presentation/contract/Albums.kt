package org.dogadaev.lastfm.albums.presentation.contract

import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import org.dogadaev.lastfm.albums.data.model.AlbumsViewModel
import org.dogadaev.lastfm.statical.mvp.BasePresenter
import org.dogadaev.lastfm.statical.mvp.BaseView

interface Albums {
    interface View : BaseView {
        @StateStrategyType(AddToEndSingleStrategy::class)
        fun onState(state: State)
    }

    abstract class Presenter : BasePresenter<View>() {
        abstract fun init(artist: String, mbid: String?)
        abstract fun loadMoreAlbums()
    }

    sealed class State {
        class OnDisplay(val viewModel: AlbumsViewModel) : State()
        object OnLoading : State()
        class OnError(val message: String) : State()
    }
}