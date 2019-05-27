package org.dogadaev.lastfm.net.data.model.search

import com.google.gson.annotations.SerializedName

data class SearchQuery(
    @SerializedName("#text") val text: String = "",
    val role: String = "",
    val searchTerms: String = "",
    val startPage: Int = 0
)
