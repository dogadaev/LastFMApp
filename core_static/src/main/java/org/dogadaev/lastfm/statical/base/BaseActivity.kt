package org.dogadaev.lastfm.statical.base

import androidx.annotation.IdRes
import org.dogadaev.lastfm.statical.androidx.moxy.MvpAppCompatActivity
import org.dogadaev.lastfm.statical.mvp.BaseView
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.NavigatorHolder
import org.koin.android.ext.android.get
import ru.terrakok.cicerone.android.support.SupportAppNavigator

abstract class BaseActivity : MvpAppCompatActivity(), BaseView {
    private val navigatorHolder: NavigatorHolder = get()

    @IdRes
    protected open val containerId: Int = -1

    @Suppress("LeakingThis")
    private val navigator: Navigator = SupportAppNavigator(this, containerId)

    override fun onResume() {
        super.onResume()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        navigatorHolder.removeNavigator()
    }

}