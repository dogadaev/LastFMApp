package org.dogadaev.lastfm.details.presentation.contract

import androidx.lifecycle.LiveData
import org.dogadaev.lastfm.details.data.model.DetailsModel
import org.dogadaev.lastfm.statical.mvp.BaseViewModel

interface Details {
    interface View

    abstract class ViewModel : BaseViewModel() {
        abstract val albumLiveData: LiveData<State>
    }

    sealed class State {
        class OnDisplay(val viewModel: DetailsModel) : State()
        object OnLoading : State()
        class OnError(val message: String) : State()
    }
}