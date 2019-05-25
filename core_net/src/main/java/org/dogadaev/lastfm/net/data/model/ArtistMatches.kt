package org.dogadaev.lastfm.net.data.model

import com.google.gson.annotations.SerializedName

data class ArtistMatches(
    @SerializedName("artist") val artists: List<Artist> = emptyList()
)