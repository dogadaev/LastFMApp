package org.dogadaev.lastfm.start.presentation.contract

import android.content.Intent
import moxy.MvpPresenter
import moxy.MvpView

interface Start {
    interface View : MvpView

    abstract class Presenter : MvpPresenter<View>() {
        abstract fun handleIntent(intent: Intent?)
    }
}
