package org.dogadaev.lastfm.details.data.repository

import org.dogadaev.lastfm.details.data.model.AlbumCommon

interface DetailsRepository {
    suspend fun getAlbumDetails(artist: String, album: String): AlbumCommon
}