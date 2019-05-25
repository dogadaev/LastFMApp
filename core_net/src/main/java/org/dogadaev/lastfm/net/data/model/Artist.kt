package org.dogadaev.lastfm.net.data.model

import com.google.gson.annotations.SerializedName

data class Artist(
    val name: String = "",
    val listeners: Int = 0,
    val url: String = "",
    val mbid: String = "",
    val streamable: Boolean = false,
    @SerializedName("image") val images: List<Image> = emptyList()
) {
    fun getImageUrl(size: Image.Size = Image.Size.Medium): String =
        images.firstOrNull { it.size == size }?.url ?: ""
}