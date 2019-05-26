package org.dogadaev.lastfm.albums.data.model


data class AlbumsViewModel(
    val artist: String,
    val albums: List<AlbumCommon>,
    val addedCount: Int = 0
)