package org.dogadaev.lastfm.details.data.repository

import org.dogadaev.lastfm.net.data.model.details.AlbumInfo

interface DetailsRepository {
    suspend fun getAlbumDetails(artist: String, album: String, mbid: String?): AlbumInfo
}