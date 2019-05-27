package org.dogadaev.lastfm.main.data.model

import org.dogadaev.lastfm.db.entity.AlbumDb

data class MainViewModel(
    val albums: List<AlbumDb>
)