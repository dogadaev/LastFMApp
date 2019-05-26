package org.dogadaev.lastfm.albums.presentation.presenter

import com.arellomobile.mvp.InjectViewState
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.dogadaev.lastfm.albums.data.model.AlbumCommon
import org.dogadaev.lastfm.albums.data.model.AlbumsViewModel
import org.dogadaev.lastfm.albums.data.repository.AlbumsRepository
import org.dogadaev.lastfm.albums.presentation.contract.Albums
import org.dogadaev.lastfm.navigation.BaseDetailsScreen
import org.dogadaev.lastfm.net.data.model.getImageUrl
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

    private var albums: MutableList<AlbumCommon> = mutableListOf()
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

    override fun saveDeleteAlbum(position: Int) {
        job?.cancel()
        job = launch {
            try {
                val album = albums[position]
                val savedAlbum = if (album.isStored) {
                    albumsRepository.deleteAlbum(album.artist.name, album.name)
                    album.copy(isStored = false, isBeingProcessed = false)
                } else {
                    albumsRepository.saveAlbum(album)
                    album.copy(isStored = true, isBeingProcessed = false)
                }

                albums = albums.map { if (it.name == savedAlbum.name) savedAlbum else it }.toMutableList()

                val viewModel = AlbumsViewModel(artist, albums)
                viewState.onState(Albums.State.OnDisplay(viewModel))
            } catch (e: CancellationException) {
                // no op
            } catch (e: Exception) {
                viewState.onState(Albums.State.OnError(e.localizedMessage))
                // todo: остановить прогрессбар
            }
        }
    }

    private fun getArtistInfo(artist: String, mbid: String?) {
        job?.cancel()
        job = launch {
            try {
                val topAlbumsResult = albumsRepository.getTopAlbums(artist, mbid, page = currentPage)
                val attributes = topAlbumsResult.topAlbums.attributes
                val albums = topAlbumsResult.topAlbums.albums.map {
                    AlbumCommon(it.name, it.playCount, it.url, it.artist, it.images.getImageUrl())
                }

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

    private fun updateAlbums(newAlbums: List<AlbumCommon>) {
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