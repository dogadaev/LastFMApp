package org.dogadaev.lastfm.albums.data.model

import org.dogadaev.lastfm.net.data.model.albums.Album
import org.dogadaev.lastfm.net.data.model.albums.AlbumsArtist

data class AlbumsViewModel(
    val albums: List<Album>,
    val artist: AlbumsArtist
)