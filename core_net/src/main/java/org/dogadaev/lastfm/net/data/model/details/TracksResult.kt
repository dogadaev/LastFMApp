package org.dogadaev.lastfm.net.data.model.details

import com.google.gson.annotations.SerializedName

data class TracksResult(
    @SerializedName("track") val tracks: List<TrackInfo> = emptyList()
)