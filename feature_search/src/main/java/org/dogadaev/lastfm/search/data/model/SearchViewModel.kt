package org.dogadaev.lastfm.search.data.model

import org.dogadaev.lastfm.net.data.model.Artist

data class SearchViewModel(
    val searchQuery: String,
    val artists: List<Artist>,
    val newSearch: Boolean,
    val addedCount: Int = 0
)