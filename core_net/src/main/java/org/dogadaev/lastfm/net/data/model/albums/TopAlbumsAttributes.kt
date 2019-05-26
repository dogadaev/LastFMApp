package org.dogadaev.lastfm.net.data.model.albums

data class TopAlbumsAttributes(
    val artist: String = "",
    val page: Int = 0,
    val perPage: Int = 0,
    val totalPages: Int = 0,
    val total: Int = 0
)