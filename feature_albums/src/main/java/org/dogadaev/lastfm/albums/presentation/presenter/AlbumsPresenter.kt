package org.dogadaev.lastfm.albums.presentation.presenter

import com.arellomobile.mvp.InjectViewState
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.dogadaev.lastfm.albums.data.model.AlbumsViewModel
import org.dogadaev.lastfm.albums.data.repository.AlbumsRepository
import org.dogadaev.lastfm.albums.presentation.contract.Albums
import org.dogadaev.lastfm.navigation.BaseDetailsScreen
import org.dogadaev.lastfm.net.data.model.albums.Album
import org.koin.core.get
import org.koin.core.parameter.parametersOf
import ru.terrakok.cicerone.Router

@InjectViewState
class AlbumsPresenter(
    private val albumsRepository: AlbumsRepository,
    private val router: Router
) : Albums.Presenter() {

    private var job: Job? = null

    private lateinit var artist: String
    private var mbid: String? = null

    private var albums: MutableList<Album> = mutableListOf()
    private var currentPage: Int = 1
    private var totalPages: Int = 0

    override fun init(artist: String, mbid: String?) {
        this.artist = artist
        this.mbid = mbid
        getArtistInfo(artist, mbid)
    }

    override fun loadMoreAlbums() {
        if (currentPage >= totalPages) return
        currentPage++
        getArtistInfo(artist, mbid)
    }

    override fun openAlbumInfo(position: Int) {
        val album = albums[position]
        val detailsScreen = get<BaseDetailsScreen> { parametersOf(album.artist.name, album.name, mbid) }
        router.navigateTo(detailsScreen)
    }

    private fun getArtistInfo(artist: String, mbid: String?) {
        job?.cancel()
        job = launch {
            try {
                val topAlbumsResult = albumsRepository.getTopAlbums(artist, mbid, page = currentPage)
                val attributes = topAlbumsResult.topAlbums.attributes
                val albums = topAlbumsResult.topAlbums.albums

                this@AlbumsPresenter.currentPage = attributes.page
                this@AlbumsPresenter.totalPages = attributes.totalPages

                updateAlbums(albums)
            } catch (e: CancellationException) {
                // no op
            } catch (e: Exception) {
                viewState.onState(Albums.State.OnError(e.localizedMessage))
            }
        }
    }

    private fun updateAlbums(newAlbums: List<Album>) {
        val addedCount = if (currentPage > 1) {
            albums.addAll(newAlbums)
            newAlbums.size
        } else {
            albums = newAlbums.toMutableList()
            0
        }

        val viewModel = AlbumsViewModel(artist, albums, addedCount = addedCount)
        viewState.onState(Albums.State.OnDisplay(viewModel))
    }
}