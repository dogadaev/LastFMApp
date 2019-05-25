package org.dogadaev.lastfm.search.presentation.view.fragment

import android.os.Bundle
import android.view.View
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import kotlinx.android.parcel.Parcelize
import kotlinx.android.synthetic.main.fragment_search.*
import org.dogadaev.lastfm.navigation.SearchScreen
import org.dogadaev.lastfm.navigation.newFragmentWithScreen
import org.dogadaev.lastfm.search.R
import org.dogadaev.lastfm.search.presentation.contract.Search
import org.dogadaev.lastfm.search.presentation.view.adapter.SearchAdapter
import org.dogadaev.lastfm.statical.base.BaseFragment
import org.dogadaev.lastfm.statical.messages.showInfoMessage
import org.koin.android.ext.android.get

class SearchFragment : BaseFragment(), Search.View {

    override val layoutRes: Int
        get() = R.layout.fragment_search

    @InjectPresenter
    internal lateinit var presenter: Search.Presenter

    @ProvidePresenter
    fun provide(): Search.Presenter = get()

    private lateinit var adapter: SearchAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()
        setupRecycler()
    }

    override fun onState(state: Search.State) {
        when (state) {
            is Search.State.OnDisplay -> {
                setLoadingEnabled(false)

                val viewModel = state.viewModel

                searchField.setText(viewModel.searchQuery)
                adapter.submitList(viewModel.artists) {
                    if (viewModel.newSearch) {
                        recycler.scrollToPosition(0)
                    } else {
                        adapter.notifyItemRangeInserted(adapter.currentList.size, viewModel.addedCount)
                    }
                }
            }
            is Search.State.OnLoading -> setLoadingEnabled(true)
            is Search.State.OnError -> {
                setLoadingEnabled(false)
                showInfoMessage(requireContext(), state.message)
            }
        }
    }

    private fun setupListeners() {
        searchButton.setOnClickListener {
            presenter.performNewSearch(
                searchField.text.toString()
            )
        }
    }

    private fun setupRecycler() {
        adapter = SearchAdapter()
        recycler.layoutManager = LinearLayoutManager(context)
        recycler.adapter = adapter
    }


    private fun setLoadingEnabled(enabled: Boolean) {
        progressBar.isVisible = enabled
        searchButton.isInvisible = enabled

        adapter.onBottomReached(if (enabled) null else presenter::loadNextPage)
    }

    @Parcelize
    class Screen : SearchScreen() {
        override fun getFragment(): SearchFragment = newFragmentWithScreen()
    }
}