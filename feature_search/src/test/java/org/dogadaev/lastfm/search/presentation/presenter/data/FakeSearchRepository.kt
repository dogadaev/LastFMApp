package org.dogadaev.lastfm.search.presentation.presenter.data

import org.dogadaev.lastfm.net.data.model.search.ArtistSearchResult
import org.dogadaev.lastfm.search.data.repository.SearchRepository

class FakeSearchRepository : SearchRepository {
    override suspend fun searchForArtist(artistName: String, page: Int?, limit: Int?): ArtistSearchResult {
        TODO("Implement fake data source")
    }
}