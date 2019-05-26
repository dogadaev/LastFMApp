package org.dogadaev.lastfm.albums.presentation.view.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import kotlinx.android.parcel.Parcelize
import kotlinx.android.synthetic.main.activity_albums.*
import org.dogadaev.lastfm.albums.R
import org.dogadaev.lastfm.navigation.AlbumsScreen
import org.dogadaev.lastfm.navigation.BaseAlbumsScreen
import org.dogadaev.lastfm.navigation.getScreen
import org.dogadaev.lastfm.navigation.newIntentWithScreen
import org.dogadaev.lastfm.statical.base.BaseActivity
import org.koin.android.ext.android.get
import org.koin.core.parameter.parametersOf
import ru.terrakok.cicerone.Router


class AlbumsActivity : BaseActivity() {
    override val containerId: Int
        get() = R.id.fragmentContainer

    private val router: Router = get()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_albums)

        setupToolbar()

        if (savedInstanceState == null) {
            router.newRootScreen(get<AlbumsScreen> {
                val screen = getScreen<Screen>()
                parametersOf(screen.artist, screen.mbid)
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
    class Screen(val artist: String, val mbid: String?) : BaseAlbumsScreen() {
        override fun getActivityIntent(context: Context): Intent =
            newIntentWithScreen<AlbumsActivity, Screen>(context)
    }
}
