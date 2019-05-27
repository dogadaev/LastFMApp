package org.dogadaev.lastfm.net.data.model.search

import com.google.gson.annotations.SerializedName
import org.dogadaev.lastfm.net.data.model.Image

data class SearchArtist(
    val name: String = "",
    val listeners: Int = 0,
    val url: String = "",
    val mbid: String = "",
    val streamable: Boolean = false,
    @SerializedName("image") val images: List<Image> = emptyList()
)