package org.dogadaev.lastfm.net.data.model.albums

import com.google.gson.annotations.SerializedName

data class TopAlbumsResult(
    @SerializedName("topalbums") val topAlbums: TopAlbums = TopAlbums()
)