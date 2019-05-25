package org.dogadaev.lastfm.albums.data.repository

import org.dogadaev.lastfm.net.api.API
import org.dogadaev.lastfm.net.data.model.albums.TopAlbumsResult

class AlbumsRepositoryImpl(
    private val api: API,
    private val apiKey: String
) : AlbumsRepository {

    override suspend fun getTopAlbums(artist: String, mbid: String?, page: Int?, limit: Int?): TopAlbumsResult {
        return if (mbid == null) getTopAlbumsByArtist(artist, page, limit)
        else getTopAlbumsById(mbid, page, limit)
    }

    private suspend fun getTopAlbumsByArtist(artist: String, page: Int?, limit: Int?): TopAlbumsResult =
        api.getTopAlbumsAsync(apiKey, artist = artist, mbid = null, page = page, limit = limit)

    private suspend fun getTopAlbumsById(mbid: String, page: Int?, limit: Int?): TopAlbumsResult =
        api.getTopAlbumsAsync(apiKey, artist = null, mbid = mbid, page = page, limit = limit)
}