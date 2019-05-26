package org.dogadaev.lastfm.details.data.model

import org.dogadaev.lastfm.net.data.model.details.TrackInfo

data class DetailsViewModel(
    val name: String,
    val artist: String,
    val tracks: List<TrackInfo>,
    val imageUrl: String
)