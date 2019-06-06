package org.dogadaev.lastfm.main.presentation.view.fragment

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.parcel.Parcelize
import kotlinx.android.synthetic.main.fragment_main.*
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import org.dogadaev.lastfm.main.R
import org.dogadaev.lastfm.main.presentation.contract.Main
import org.dogadaev.lastfm.main.presentation.view.adapter.MainAdapter
import org.dogadaev.lastfm.navigation.MainScreen
import org.dogadaev.lastfm.navigation.newFragmentWithScreen
import org.dogadaev.lastfm.statical.base.BaseFragment
import org.dogadaev.lastfm.statical.messages.showInfoMessage
import org.koin.android.ext.android.get

class MainFragment : BaseFragment(), Main.View {
    override val layoutRes: Int
        get() = R.layout.fragment_main

    @InjectPresenter
    internal lateinit var presenter: Main.Presenter

    @ProvidePresenter
    fun provide(): Main.Presenter = get()

    private lateinit var adapter: MainAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        setUpToolbar()
        setupRecycler()
    }

    private fun setUpToolbar() {
        val activity = requireActivity() as AppCompatActivity
        activity.setSupportActionBar(toolbar)
    }

    override fun onState(state: Main.State) {
        when (state) {
            is Main.State.OnDisplay -> {
                setLoadingEnabled(false)

                val viewModel = state.viewModel

                adapter.submitList(viewModel.albums) {
                    placeholder.isVisible = adapter.currentList.isEmpty()
                }
            }
            Main.State.OnLoading -> setLoadingEnabled(true)
            is Main.State.OnError -> {
                setLoadingEnabled(false)
                showInfoMessage(requireContext(), state.message)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.main_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.search -> {
                presenter.openSearch()
            }
        }
        return true
    }

    override fun onResume() {
        super.onResume()
        presenter.refresh()
    }

    private fun setLoadingEnabled(enabled: Boolean) {
        recycler.isInvisible = enabled
        progress.isVisible = enabled
    }

    private fun setupRecycler() {
        adapter = MainAdapter()

        adapter.onAlbumClicked(presenter::openDetails)
        recycler.adapter = adapter
        recycler.layoutManager = LinearLayoutManager(requireContext())
    }

    @Parcelize
    class Screen : MainScreen() {
        override fun getFragment(): MainFragment = newFragmentWithScreen()
    }
}