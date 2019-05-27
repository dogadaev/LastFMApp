package org.dogadaev.lastfm.net.data.model.search

import com.google.gson.annotations.SerializedName

data class ArtistMatches(
    @SerializedName("artist") val artists: List<SearchArtist> = emptyList()
)