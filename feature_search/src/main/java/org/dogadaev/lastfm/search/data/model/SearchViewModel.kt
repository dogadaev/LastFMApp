package org.dogadaev.lastfm.search.data.model

import org.dogadaev.lastfm.net.data.model.search.SearchArtist

data class SearchViewModel(
    val searchQuery: String,
    val artists: List<SearchArtist>,
    val newSearch: Boolean,
    val addedCount: Int = 0
)