package org.dogadaev.lastfm.albums.data.repository

import org.dogadaev.lastfm.net.data.model.albums.TopAlbumsResult

interface AlbumsRepository {
    suspend fun getTopAlbums(artist: String, mbid: String?, page: Int? = null, limit: Int? = null): TopAlbumsResult
}