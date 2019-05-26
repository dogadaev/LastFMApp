package org.dogadaev.lastfm.net.data.model.details

import com.google.gson.annotations.SerializedName
import org.dogadaev.lastfm.net.data.model.Image

data class AlbumInfo(
    val name: String = "",
    val artist: String = "",
    val mbid: String = "",
    val url: String = "",
    @SerializedName("image") val images: List<Image> = emptyList(),
    val listeners: Int = 0,
    val playCount: Int = 0,
    @SerializedName("tracks") val tracksResult: TracksResult = TracksResult()
)