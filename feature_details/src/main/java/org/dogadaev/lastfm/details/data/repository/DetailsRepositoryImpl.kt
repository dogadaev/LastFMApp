package org.dogadaev.lastfm.details.data.repository

import org.dogadaev.lastfm.net.api.API
import org.dogadaev.lastfm.net.data.model.details.AlbumInfo

class DetailsRepositoryImpl(
    private val api: API,
    private val apiKey: String
) : DetailsRepository {
    override suspend fun getAlbumDetails(artist: String, album: String, mbid: String?): AlbumInfo {
        return getAlbumDetailsByName(artist, album)
    }

    private suspend fun getAlbumDetailsById(mbid: String): AlbumInfo =
        api.getAlbumInfoAsync(
            apiKey,
            artist = null,
            album = null,
            mbid = mbid,
            lang = null,
            autoCorrect = null
        ).albumInfo

    private suspend fun getAlbumDetailsByName(artist: String, album: String): AlbumInfo =
        api.getAlbumInfoAsync(apiKey, artist, album, mbid = null, lang = null, autoCorrect = null).albumInfo
}