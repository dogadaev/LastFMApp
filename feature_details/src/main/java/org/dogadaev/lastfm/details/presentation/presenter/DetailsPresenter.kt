package org.dogadaev.lastfm.details.presentation.presenter

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import moxy.InjectViewState
import org.dogadaev.lastfm.details.R
import org.dogadaev.lastfm.details.data.model.DetailsViewModel
import org.dogadaev.lastfm.details.data.repository.DetailsRepository
import org.dogadaev.lastfm.details.presentation.contract.Details
import org.dogadaev.lastfm.net.data.repository.NetworkChecker
import org.dogadaev.lastfm.statical.resources.ResourceProvider
import java.net.UnknownHostException

@InjectViewState
class DetailsPresenter(
    private val detailsRepository: DetailsRepository,
    private val networkChecker: NetworkChecker,
    private val resourceProvider: ResourceProvider
) : Details.Presenter() {

    private var job: Job? = null

    override fun init(artist: String, album: String) {
        viewState.onState(Details.State.OnLoading)

        job?.cancel()
        job = launch {
            try {
                val albumInfo = detailsRepository.getAlbumDetails(artist, album)

                val viewModel = DetailsViewModel(
                    albumInfo.name,
                    albumInfo.artist,
                    albumInfo.tracks,
                    albumInfo.imageUrl,
                    networkChecker.isNetworkConnected
                )

                viewState.onState(Details.State.OnDisplay(viewModel))
            } catch (t: Throwable) {
                processErrors(t)
            }
        }
    }

    private fun processErrors(e: Throwable) {
        when (e) {
            is CancellationException -> {
                // no op
            }
            is UnknownHostException ->
                viewState.onState(Details.State.OnError(resourceProvider.getString(R.string.error_no_internet)))
            is Exception ->
                viewState.onState(
                    Details.State.OnError(
                        e.localizedMessage ?: resourceProvider.getString(R.string.error_unknown)
                    )
                )
        }
    }
}