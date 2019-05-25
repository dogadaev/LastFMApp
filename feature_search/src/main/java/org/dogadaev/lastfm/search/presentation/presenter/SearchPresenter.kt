package org.dogadaev.lastfm.search.presentation.presenter

import com.arellomobile.mvp.InjectViewState
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.dogadaev.lastfm.search.data.model.SearchViewModel
import org.dogadaev.lastfm.search.data.repository.SearchRepository
import org.dogadaev.lastfm.search.presentation.contract.Search
import java.lang.Exception

@InjectViewState
class SearchPresenter(
    private val searchRepository: SearchRepository
) : Search.Presenter() {

    private var searchJob: Job? = null

    override fun performNewSearch(searchQuery: String) {
        viewState.onState(Search.State.OnLoading)

        searchJob?.cancel()
        searchJob = launch {
            try {
                val searchModel = searchRepository.searchForArtist(searchQuery)
                val searchViewModel = SearchViewModel(
                    searchQuery,
                    searchModel.results.artistmatches.artists
                )
                viewState.onState(Search.State.OnDisplay(searchViewModel))
            } catch (e: CancellationException) {
                // no op
            } catch (e: Exception) {
                viewState.onState(Search.State.OnError(e.localizedMessage))
            }
        }
    }

    override fun loadNextPage() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}