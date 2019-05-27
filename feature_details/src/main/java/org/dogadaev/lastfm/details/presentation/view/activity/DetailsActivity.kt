package org.dogadaev.lastfm.details.presentation.view.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import kotlinx.android.parcel.Parcelize
import kotlinx.android.synthetic.main.activity_details.*
import org.dogadaev.lastfm.details.R
import org.dogadaev.lastfm.navigation.BaseDetailsScreen
import org.dogadaev.lastfm.navigation.DetailsScreen
import org.dogadaev.lastfm.navigation.getScreen
import org.dogadaev.lastfm.navigation.newIntentWithScreen
import org.dogadaev.lastfm.statical.base.BaseActivity
import org.koin.android.ext.android.get
import org.koin.core.parameter.parametersOf
import ru.terrakok.cicerone.Router

class DetailsActivity : BaseActivity() {
    override val containerId: Int
        get() = R.id.fragmentContainer

    private val router: Router = get()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        setupToolbar()

        if (savedInstanceState == null) {
            router.newRootScreen(get<DetailsScreen> {
                val screen = getScreen<Screen>()
                parametersOf(screen.artist, screen.album)
            })
        }
    }

    private fun setupToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onHomePressed()
        }
        return true
    }

    private fun onHomePressed() {
        router.exit()
    }

    @Parcelize
    class Screen(val artist: String, val album: String) : BaseDetailsScreen() {
        override fun getActivityIntent(context: Context): Intent =
            newIntentWithScreen<DetailsActivity, Screen>(context)
    }
}
