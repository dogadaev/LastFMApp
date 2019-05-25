package org.dogadaev.lastfm.net.data.model

import com.google.gson.annotations.SerializedName

data class ArtistMatches(
    @SerializedName("opensearch:Query") val query: SearchQuery? = null,
    @SerializedName("opensearch:totalResults") val totalResults: Int = 0,
    @SerializedName("opensearch:startIndex") val startIndex: Int = 0,
    @SerializedName("opensearch:itemsPerPage") val itemsPerPage: Int = 0,
    val artist: List<Artist> = emptyList()
)