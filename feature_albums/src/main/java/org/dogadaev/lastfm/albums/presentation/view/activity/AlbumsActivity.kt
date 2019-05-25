package org.dogadaev.lastfm.albums.presentation.view.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import kotlinx.android.parcel.Parcelize
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

        if (savedInstanceState == null) {
            router.newRootScreen(get<AlbumsScreen> {
                val screen = getScreen<Screen>()
                parametersOf(screen.artist, screen.mbid)
            })
        }
    }

    @Parcelize
    class Screen(val artist: String, val mbid: String?) : BaseAlbumsScreen() {
        override fun getActivityIntent(context: Context): Intent =
            newIntentWithScreen<AlbumsActivity, Screen>(context)
    }
}
