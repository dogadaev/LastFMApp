package org.dogadaev.lastfm.albums.presentation.presenter

import com.arellomobile.mvp.InjectViewState
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.dogadaev.lastfm.albums.R
import org.dogadaev.lastfm.albums.data.model.AlbumCommon
import org.dogadaev.lastfm.albums.data.model.AlbumsViewModel
import org.dogadaev.lastfm.albums.data.repository.AlbumsRepository
import org.dogadaev.lastfm.albums.presentation.contract.Albums
import org.dogadaev.lastfm.navigation.BaseDetailsScreen
import org.dogadaev.lastfm.net.data.model.getImageUrl
import org.dogadaev.lastfm.statical.resources.ResourceProvider
import org.koin.core.get
import org.koin.core.parameter.parametersOf
import ru.terrakok.cicerone.Router
import java.net.UnknownHostException

@InjectViewState
class AlbumsPresenter(
    private val albumsRepository: AlbumsRepository,
    private val router: Router,
    private val resourceProvider: ResourceProvider
) : Albums.Presenter() {

    private var job: Job? = null

    private lateinit var artist: String

    private var albums: MutableList<AlbumCommon> = mutableListOf()
    private var currentPage: Int = 1
    private var totalPages: Int = 0

    override fun init(artist: String) {
        this.artist = artist
        getArtistInfo(artist)
    }

    override fun loadMoreAlbums() {
        if (currentPage >= totalPages) return
        currentPage++
        getArtistInfo(artist)
    }

    override fun openAlbumInfo(position: Int) {
        val album = albums[position]
        val detailsScreen = get<BaseDetailsScreen> { parametersOf(album.artist.name, album.name) }
        router.navigateTo(detailsScreen)
    }

    override fun saveDeleteAlbum(position: Int) {
        val album = albums[position]

        job?.cancel()
        job = launch {
            try {
                val savedAlbum = if (album.isStored) {
                    albumsRepository.deleteAlbum(album.artist.name, album.name)
                    album.copy(isStored = false, isBeingProcessed = false)
                } else {
                    albumsRepository.saveAlbum(album)
                    album.copy(isStored = true, isBeingProcessed = false)
                }

                updateAlbum(savedAlbum)
            } catch (e: UnknownHostException) {
                viewState.onState(
                    Albums.State.OnError(
                        resourceProvider.getString(R.string.error_no_internet)
                    )
                )
            } catch (e: Exception) {
                viewState.onState(
                    Albums.State.OnError(
                        e.localizedMessage ?: resourceProvider.getString(R.string.error_unknown)
                    )
                )

                val albumToReplace = album.copy(isStored = album.isStored, isBeingProcessed = false)
                updateAlbum(albumToReplace)
            }
        }
    }

    private fun getArtistInfo(artist: String) {
        job?.cancel()
        job = launch {
            try {
                val savedAlbums = albumsRepository.getSavedAlbums(artist).toMutableList()
                val topAlbumsResult = albumsRepository.getTopAlbums(artist, page = currentPage)
                val attributes = topAlbumsResult.topAlbums.attributes
                val albums = topAlbumsResult.topAlbums.albums.map { netAlbum ->
                    val isStored =
                        savedAlbums.removeAll { dbAlbum ->
                            dbAlbum.name == netAlbum.name && dbAlbum.artist == netAlbum.artist.name
                        }

                    AlbumCommon(
                        netAlbum.name,
                        netAlbum.playCount,
                        netAlbum.url,
                        netAlbum.artist,
                        netAlbum.images.getImageUrl(),
                        isStored = isStored
                    )
                }

                this@AlbumsPresenter.currentPage = attributes.page
                this@AlbumsPresenter.totalPages = attributes.totalPages

                updateAlbums(albums)
            } catch (t: Throwable) {
                processErrors(t)
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

        displayAlbums(addedCount)
    }

    private fun updateAlbum(savedAlbum: AlbumCommon) {
        albums = albums.map { if (it.name == savedAlbum.name) savedAlbum else it }.toMutableList()
        displayAlbums()
    }

    private fun displayAlbums(addedCount: Int = 0) {
        val viewModel = AlbumsViewModel(artist, albums, addedCount = addedCount)
        viewState.onState(Albums.State.OnDisplay(viewModel))
    }

    private fun processErrors(e: Throwable) {
        when (e) {
            is CancellationException -> {
                // no op
            }
            is UnknownHostException ->
                viewState.onState(Albums.State.OnError(resourceProvider.getString(R.string.error_no_internet)))
            is Exception ->
                viewState.onState(
                    Albums.State.OnError(
                        e.localizedMessage ?: resourceProvider.getString(R.string.error_unknown)
                    )
                )
        }
    }
}