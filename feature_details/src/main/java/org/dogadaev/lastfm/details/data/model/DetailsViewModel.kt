package org.dogadaev.lastfm.details.data.model

data class DetailsViewModel(
    val name: String,
    val artist: String,
    val tracks: List<TrackCommon>,
    val imageUrl: String,
    val isOnline: Boolean
)