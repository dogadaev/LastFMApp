package org.dogadaev.lastfm.net.data.model.details

import com.google.gson.annotations.SerializedName

data class AlbumInfoResult(
    @SerializedName("album") val albumInfo: AlbumInfo = AlbumInfo()
)