package org.dogadaev.lastfm.details.presentation.presenter

import com.arellomobile.mvp.InjectViewState
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.dogadaev.lastfm.details.data.model.DetailsViewModel
import org.dogadaev.lastfm.details.data.repository.DetailsRepository
import org.dogadaev.lastfm.details.presentation.contract.Details
import org.dogadaev.lastfm.net.data.model.getImageUrl

@InjectViewState
class DetailsPresenter(
    private val detailsRepository: DetailsRepository
) : Details.Presenter() {

    private var job: Job? = null

    override fun init(artist: String, album: String, mbid: String?) {
        viewState.onState(Details.State.OnLoading)

        job?.cancel()
        job = launch {
            try {
                val albumInfo = detailsRepository.getAlbumDetails(artist, album, mbid)

                val viewModel = DetailsViewModel(
                    albumInfo.name,
                    albumInfo.artist,
                    albumInfo.tracksResult.tracks,
                    albumInfo.images.getImageUrl()
                )

                viewState.onState(Details.State.OnDisplay(viewModel))
            } catch (e: CancellationException) {
                // no op
            } catch (e: Exception) {
                viewState.onState(Details.State.OnError(e.localizedMessage))
            }
        }
    }
}