package org.dogadaev.lastfm.main.presentation.presenter

import kotlinx.coroutines.launch
import moxy.InjectViewState
import org.dogadaev.lastfm.db.entity.AlbumDb
import org.dogadaev.lastfm.main.R
import org.dogadaev.lastfm.main.data.model.MainViewModel
import org.dogadaev.lastfm.main.data.repository.AlbumsRepository
import org.dogadaev.lastfm.main.presentation.contract.Main
import org.dogadaev.lastfm.navigation.BaseDetailsScreen
import org.dogadaev.lastfm.navigation.BaseSearchScreen
import org.dogadaev.lastfm.statical.resources.ResourceProvider
import org.koin.core.get
import org.koin.core.parameter.parametersOf
import ru.terrakok.cicerone.Router

@InjectViewState
class MainPresenter(
    private val albumsRepository: AlbumsRepository,
    private val router: Router,
    private val resourceProvider: ResourceProvider
) : Main.Presenter() {

    private lateinit var albums: List<AlbumDb>

    override fun refresh() {
        launch {
            try {
                val albums = albumsRepository.getAlbums()
                val viewModel = MainViewModel(albums)

                this@MainPresenter.albums = albums
                viewState.onState(Main.State.OnDisplay(viewModel))
            } catch (e: Exception) {
                viewState.onState(
                    Main.State.OnError(
                        e.localizedMessage ?: resourceProvider.getString(R.string.error_unknown)
                    )
                )
            }
        }
    }

    override fun openDetails(position: Int) {
        val album = albums[position]
        val detailsScreen = get<BaseDetailsScreen> {
            parametersOf(album.artist, album.name)
        }

        router.navigateTo(detailsScreen)
    }

    override fun openSearch() {
        router.navigateTo(get<BaseSearchScreen>())
    }

}