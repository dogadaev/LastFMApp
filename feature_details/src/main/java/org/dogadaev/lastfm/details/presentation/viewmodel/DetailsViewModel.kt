package org.dogadaev.lastfm.details.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.launch
import org.dogadaev.lastfm.details.R
import org.dogadaev.lastfm.details.data.model.DetailsModel
import org.dogadaev.lastfm.details.data.repository.DetailsRepository
import org.dogadaev.lastfm.details.presentation.contract.Details
import org.dogadaev.lastfm.net.data.repository.NetworkChecker
import org.dogadaev.lastfm.statical.resources.ResourceProvider
import java.net.UnknownHostException

class DetailsViewModel(
    artist: String,
    album: String,
    private val detailsRepository: DetailsRepository,
    private val resourceProvider: ResourceProvider,
    private val networkChecker: NetworkChecker
) : Details.ViewModel() {
    override val albumLiveData = MutableLiveData<Details.State>()

    init {
        onState(Details.State.OnLoading)

        viewModelScope.launch {
            try {
                val albumInfo = detailsRepository.getAlbumDetails(artist, album)

                val viewModel = DetailsModel(
                    albumInfo.name,
                    albumInfo.artist,
                    albumInfo.tracks,
                    albumInfo.imageUrl,
                    networkChecker.isNetworkConnected
                )

                onState(Details.State.OnDisplay(viewModel))
            } catch (e: Exception) {
                processErrors(e)
            }
        }
    }

    private fun onState(state: Details.State) {
        albumLiveData.value = state
    }

    private fun processErrors(e: Exception) {
        when (e) {
            is CancellationException -> {
                // no op
            }
            is UnknownHostException ->
                onState(Details.State.OnError(resourceProvider.getString(R.string.error_no_internet)))
            else  ->
                onState(
                    Details.State.OnError(
                        e.localizedMessage ?: resourceProvider.getString(R.string.error_unknown)
                    )
                )
        }
    }
}