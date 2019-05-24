package org.dogadaev.statical.base

import org.dogadaev.statical.androidx.moxy.MvpAppCompatActivity
import org.dogadaev.statical.mvp.BaseView
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.NavigatorHolder
import org.koin.android.ext.android.get
import ru.terrakok.cicerone.android.support.SupportAppNavigator

abstract class BaseActivity : MvpAppCompatActivity(), BaseView {
    private val navigatorHolder: NavigatorHolder = get()

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