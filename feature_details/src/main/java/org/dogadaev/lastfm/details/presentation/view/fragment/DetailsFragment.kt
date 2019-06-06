package org.dogadaev.lastfm.details.presentation.view.fragment

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.parcel.Parcelize
import kotlinx.android.synthetic.main.fragment_details.*
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import org.dogadaev.lastfm.details.R
import org.dogadaev.lastfm.details.presentation.contract.Details
import org.dogadaev.lastfm.details.presentation.view.adapter.DetailsAdapter
import org.dogadaev.lastfm.navigation.DetailsScreen
import org.dogadaev.lastfm.navigation.getScreen
import org.dogadaev.lastfm.navigation.newFragmentWithScreen
import org.dogadaev.lastfm.statical.base.BaseFragment
import org.dogadaev.lastfm.statical.media.ImageLoader
import org.dogadaev.lastfm.statical.messages.showInfoMessage
import org.koin.android.ext.android.get

class DetailsFragment : BaseFragment(), Details.View {
    override val layoutRes: Int
        get() = R.layout.fragment_details

    @InjectPresenter
    internal lateinit var presenter: Details.Presenter

    @ProvidePresenter
    fun provide(): Details.Presenter {
        val presenter: Details.Presenter = get()
        val screen = getScreen<Screen>()

        presenter.init(screen.artist, screen.album)
        return presenter
    }

    private val imageLoader: ImageLoader = get()
    private lateinit var adapter: DetailsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecycler()
    }

    override fun onState(state: Details.State) {
        when (state) {
            is Details.State.OnDisplay -> {
                val viewModel = state.viewModel

                adapter.submitList(viewModel.tracks)
                imageLoader.load {
                    src(viewModel.imageUrl)
                    target(albumCover)
                    fallback(R.drawable.ic_no_image)
                    error(R.drawable.ic_no_image)
                    placeholder(R.drawable.ic_no_image)
                    if (viewModel.isOnline) noCache()
                    placeholder.isVisible = adapter.currentList.isEmpty()
                }

                albumName.text = viewModel.name
                artistName.text = viewModel.artist

                progressBar.isVisible = false
            }
            is Details.State.OnLoading -> progressBar.isVisible = true
            is Details.State.OnError -> {
                progressBar.isVisible = false
                showInfoMessage(requireContext(), state.message)
            }
        }
    }

    private fun setupRecycler() {
        adapter = DetailsAdapter()
        recycler.adapter = adapter
        recycler.layoutManager = LinearLayoutManager(requireContext())
    }

    @Parcelize
    class Screen(val artist: String, val album: String) : DetailsScreen() {
        override fun getFragment(): DetailsFragment = newFragmentWithScreen()
    }
}