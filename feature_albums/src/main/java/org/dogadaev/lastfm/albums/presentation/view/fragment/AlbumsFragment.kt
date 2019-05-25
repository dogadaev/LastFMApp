package org.dogadaev.lastfm.albums.presentation.view.fragment

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import kotlinx.android.parcel.Parcelize
import kotlinx.android.synthetic.main.fragment_albums.*
import org.dogadaev.lastfm.albums.R
import org.dogadaev.lastfm.albums.presentation.contract.Albums
import org.dogadaev.lastfm.albums.presentation.view.adapter.AlbumsAdapter
import org.dogadaev.lastfm.navigation.AlbumsScreen
import org.dogadaev.lastfm.navigation.getScreen
import org.dogadaev.lastfm.navigation.newFragmentWithScreen
import org.dogadaev.lastfm.statical.base.BaseFragment
import org.dogadaev.lastfm.statical.messages.showInfoMessage
import org.koin.android.ext.android.get

class AlbumsFragment : BaseFragment(), Albums.View {
    override val layoutRes: Int
        get() = R.layout.fragment_albums

    @InjectPresenter
    internal lateinit var presenter: Albums.Presenter

    @ProvidePresenter
    fun provide(): Albums.Presenter {
        val presenter: Albums.Presenter = get()
        val screen = getScreen<Screen>()

        presenter.init(screen.artist, screen.mbid)
        return presenter
    }

    private lateinit var adapter: AlbumsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecycler()
    }

    override fun onState(state: Albums.State) {
        when (state) {
            is Albums.State.OnDisplay -> {
                val viewModel = state.viewModel

                adapter.submitList(viewModel.albums)
                // todo: Stop loading animation
            }
            is Albums.State.OnLoading -> {
                // todo: Start loading animation
            }
            is Albums.State.OnError -> {
                // todo: Stop loading animation
                showInfoMessage(requireContext(), state.message)
            }
        }
    }

    private fun setupRecycler() {
        adapter = AlbumsAdapter()

        recycler.layoutManager = LinearLayoutManager(context)
        recycler.adapter = adapter
    }

    @Parcelize
    class Screen(val artist: String, val mbid: String?) : AlbumsScreen() {
        override fun getFragment(): AlbumsFragment = newFragmentWithScreen()
    }
}