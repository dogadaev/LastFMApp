package org.dogadaev.lastfm.search.presentation.presenter

import com.arellomobile.mvp.InjectViewState
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.dogadaev.lastfm.navigation.BaseAlbumsScreen
import org.dogadaev.lastfm.net.data.model.search.SearchArtist
import org.dogadaev.lastfm.search.data.model.SearchViewModel
import org.dogadaev.lastfm.search.data.repository.SearchRepository
import org.dogadaev.lastfm.search.presentation.contract.Search
import org.koin.core.get
import org.koin.core.parameter.parametersOf
import ru.terrakok.cicerone.Router

@InjectViewState
class SearchPresenter(
    private val searchRepository: SearchRepository,
    private val router: Router
) : Search.Presenter() {

    private var searchJob: Job? = null

    private var artists: MutableList<SearchArtist> = mutableListOf()
    private var searchQuery: String = ""
    private var currentPage: Int = 1
    private var maxPages: Int = 0

    override fun performNewSearch(searchQuery: String) {
        this.searchQuery = searchQuery
        currentPage = 1
        artists = mutableListOf()
        loadArtists(true)
    }

    override fun loadNextPage() {
        if (currentPage >= maxPages) return
        currentPage++
        loadArtists(false)
    }

    override fun openTopAlbums(artist: String, mbid: String?) {
        val albumsScreen = get<BaseAlbumsScreen> {
            parametersOf(artist, mbid)
        }
        router.navigateTo(albumsScreen)
    }

    private fun loadArtists(newSearch: Boolean) {
        viewState.onState(Search.State.OnLoading)

        searchJob?.cancel()
        searchJob = launch {
            try {
                val searchModel = searchRepository.searchForArtist(searchQuery, page = currentPage)
                val searchResult = searchModel.results

                this@SearchPresenter.maxPages = searchResult.totalResults / searchResult.itemsPerPage
                this@SearchPresenter.currentPage = searchResult.query.startPage

                updateArtists(searchResult.artistmatches.artists, newSearch)
            } catch (e: CancellationException) {
                // no op
            } catch (e: Exception) {
                viewState.onState(Search.State.OnError(e.localizedMessage))
            }
        }
    }

    private fun updateArtists(newArtists: List<SearchArtist>, newSearch: Boolean) {
        val searchViewModel = if (newSearch) {
            artists = newArtists.toMutableList()

            SearchViewModel(
                searchQuery,
                artists,
                newSearch
            )
        } else {
            artists.addAll(newArtists)

            SearchViewModel(
                searchQuery,
                artists,
                newSearch,
                addedCount = newArtists.size
            )
        }

        viewState.onState(Search.State.OnDisplay(searchViewModel))

    }
}