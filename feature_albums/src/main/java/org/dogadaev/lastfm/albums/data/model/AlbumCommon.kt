package org.dogadaev.lastfm.albums.data.model

import org.dogadaev.lastfm.net.data.model.albums.AlbumsArtist

data class AlbumCommon(
    val name: String,
    val playCount: Int,
    val url: String,
    val artist: AlbumsArtist,
    val imageUrl: String,
    val isStored: Boolean = false,
    var isBeingProcessed: Boolean = false
)