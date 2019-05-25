package org.dogadaev.lastfm.search.data.repository

import org.dogadaev.lastfm.net.data.model.ArtistSearchModel

interface SearchRepository {
    suspend fun searchForArtist(artistName: String, page: Int? = null, limit: Int? = null): ArtistSearchModel
}