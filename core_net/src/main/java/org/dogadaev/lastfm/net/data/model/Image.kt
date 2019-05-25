package org.dogadaev.lastfm.net.data.model

import com.google.gson.annotations.SerializedName

data class Image(
    val size: Size = Size.Medium,
    @SerializedName("#text") val url: String = ""
) {
    enum class Size {
        @SerializedName("small")
        Small,
        @SerializedName("medium")
        Medium,
        @SerializedName("large")
        Large,
        @SerializedName("extralarge")
        ExtraLarge,
        @SerializedName("mega")
        Mega
    }
}