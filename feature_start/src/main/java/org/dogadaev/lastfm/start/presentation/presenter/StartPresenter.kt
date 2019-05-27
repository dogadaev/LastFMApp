package org.dogadaev.lastfm.start.presentation.presenter

import android.content.Intent
import com.arellomobile.mvp.InjectViewState
import org.dogadaev.lastfm.navigation.BaseMainScreen
import org.dogadaev.lastfm.start.presentation.contract.Start
import org.koin.core.KoinComponent
import org.koin.core.get
import ru.terrakok.cicerone.Router

@InjectViewState
class StartPresenter(
    private val router: Router
) : Start.Presenter(), KoinComponent {

    override fun handleIntent(intent: Intent?) {
        // todo: test implementation
        router.newRootScreen(get<BaseMainScreen>())
    }
}