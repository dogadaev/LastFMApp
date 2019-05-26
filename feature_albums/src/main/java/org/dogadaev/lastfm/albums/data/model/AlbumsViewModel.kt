package org.dogadaev.lastfm.albums.data.model

import org.dogadaev.lastfm.net.data.model.albums.Album

data class AlbumsViewModel(
    val artist: String,
    val albums: List<Album>,
    val addedCount: Int = 0
)