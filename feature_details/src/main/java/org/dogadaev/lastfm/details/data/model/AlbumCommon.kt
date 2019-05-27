package org.dogadaev.lastfm.details.data.model


data class AlbumCommon(
    val name: String,
    val artist: String,
    val imageUrl: String,
    val tracks: List<TrackCommon>
)