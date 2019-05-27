package org.dogadaev.lastfm.main.presentation.view.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import kotlinx.android.parcel.Parcelize
import org.dogadaev.lastfm.main.R
import org.dogadaev.lastfm.navigation.BaseMainScreen
import org.dogadaev.lastfm.navigation.MainScreen
import org.dogadaev.lastfm.navigation.newIntentWithScreen
import org.dogadaev.lastfm.statical.base.BaseActivity
import org.koin.android.ext.android.get
import ru.terrakok.cicerone.Router

class MainActivity : BaseActivity() {
    override val containerId: Int
        get() = R.id.fragmentContainer

    private val router: Router = get()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) router.newRootScreen(get<MainScreen>())
    }



    @Parcelize
    class Screen : BaseMainScreen() {
        override fun getActivityIntent(context: Context): Intent =
            newIntentWithScreen<MainActivity, Screen>(context)
    }
}
