package org.dogadaev.lastfm.db.entity

import androidx.room.Entity

@Entity
data class TrackDb(
    val name: String,
    val duration: Long
)
