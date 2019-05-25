package org.dogadaev.lastfm.net.data.model

import com.google.gson.annotations.SerializedName

data class SearchResult(
    @SerializedName("opensearch:Query") val query: SearchQuery = SearchQuery(),
    @SerializedName("opensearch:totalResults") val totalResults: Int = 0,
    @SerializedName("opensearch:startIndex") val startIndex: Int = 0,
    @SerializedName("opensearch:itemsPerPage") val itemsPerPage: Int = 0,
    val artistmatches: ArtistMatches = ArtistMatches()
)