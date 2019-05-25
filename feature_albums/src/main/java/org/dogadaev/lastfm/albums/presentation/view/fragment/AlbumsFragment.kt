package org.dogadaev.lastfm.albums.presentation.view.fragment

import android.os.Bundle
import android.view.View
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import kotlinx.android.parcel.Parcelize
import org.dogadaev.lastfm.albums.R
import org.dogadaev.lastfm.albums.presentation.contract.Albums
import org.dogadaev.lastfm.navigation.AlbumsScreen
import org.dogadaev.lastfm.navigation.getScreen
import org.dogadaev.lastfm.navigation.newFragmentWithScreen
import org.dogadaev.lastfm.statical.base.BaseFragment
import org.koin.android.ext.android.get

class AlbumsFragment : BaseFragment(), Albums.View {
    override val layoutRes: Int
        get() = R.layout.fragment_albums

    @InjectPresenter
    internal lateinit var presenter: Albums.Presenter

    @ProvidePresenter
    fun provide(): Albums.Presenter {
        val presenter: Albums.Presenter = get()
        presenter.init(
            getScreen<Screen>().artist
        )
        return presenter
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onState(state: Albums.State) {
        when (state) {
            is Albums.State.OnDisplay -> TODO()
            is Albums.State.OnLoading -> TODO()
            is Albums.State.OnError -> TODO()
        }
    }

    @Parcelize
    class Screen(val artist: String) : AlbumsScreen() {
        override fun getFragment(): AlbumsFragment = newFragmentWithScreen()
    }
}