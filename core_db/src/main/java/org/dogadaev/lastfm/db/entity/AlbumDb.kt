package org.dogadaev.lastfm.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "albums")
data class AlbumDb(
    @PrimaryKey
    val name: String,
    val artist: String,
    val imageUrl: String,
    val tracksDb: List<TrackDb>
)