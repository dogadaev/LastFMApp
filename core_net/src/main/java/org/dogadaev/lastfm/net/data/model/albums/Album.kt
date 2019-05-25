package org.dogadaev.lastfm.net.data.model.albums

import com.google.gson.annotations.SerializedName
import org.dogadaev.lastfm.net.data.model.Image

data class Album(
    val name: String = "",
    @SerializedName("playcount") val playCount: Int = 0,
    val url: String = "",
    val artist: AlbumsArtist = AlbumsArtist(),
    val images: List<Image> = emptyList()
)