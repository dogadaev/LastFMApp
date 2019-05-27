package org.dogadaev.lastfm.search.presentation.view.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.WindowManager
import kotlinx.android.parcel.Parcelize
import kotlinx.android.synthetic.main.activity_search.*
import org.dogadaev.lastfm.navigation.BaseSearchScreen
import org.dogadaev.lastfm.navigation.SearchScreen
import org.dogadaev.lastfm.navigation.newIntentWithScreen
import org.dogadaev.lastfm.search.R
import org.dogadaev.lastfm.statical.base.BaseActivity
import org.koin.android.ext.android.get
import ru.terrakok.cicerone.Router

class SearchActivity : BaseActivity() {
    override val containerId: Int
        get() = R.id.fragmentContainer

    private val router: Router = get()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
        setupToolbar()

        if (savedInstanceState == null) router.newRootScreen(get<SearchScreen>())
    }

    private fun setupToolbar() {
        setSupportActionBar(toolbar)
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
    class Screen : BaseSearchScreen() {
        override fun getActivityIntent(context: Context): Intent =
            newIntentWithScreen<SearchActivity, Screen>(context)
    }
}
