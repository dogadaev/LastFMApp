package org.dogadaev.lastfm.net.data.model.albums

import com.google.gson.annotations.SerializedName

data class TopAlbums(
    @SerializedName("album") val albums: List<Album> = emptyList(),
    @SerializedName("@attrs") val attributes: TopAlbumsAttributes = TopAlbumsAttributes()
)