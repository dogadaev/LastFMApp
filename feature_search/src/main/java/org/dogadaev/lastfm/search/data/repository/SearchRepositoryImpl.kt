package org.dogadaev.lastfm.search.data.repository

import org.dogadaev.lastfm.net.api.API
import org.dogadaev.lastfm.net.data.model.search.ArtistSearchResult

class SearchRepositoryImpl(
    private val api: API,
    private val apiKey: String
): SearchRepository {

    override suspend fun searchForArtist(artistName: String, page: Int?, limit: Int?): ArtistSearchResult =
            api.searchForArtistAsync(apiKey, artistName, page, limit)
}