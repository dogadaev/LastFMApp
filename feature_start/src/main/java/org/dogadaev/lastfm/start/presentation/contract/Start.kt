package org.dogadaev.lastfm.start.presentation.contract

import android.content.Intent
import com.arellomobile.mvp.MvpPresenter
import com.arellomobile.mvp.MvpView

interface Start {
    interface View : MvpView

    abstract class Presenter : MvpPresenter<View>() {
        abstract fun handleIntent(intent: Intent?)
    }
}
