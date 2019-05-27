package org.dogadaev.lastfm.main.data.repository

import org.dogadaev.lastfm.db.entity.AlbumDb

interface AlbumsRepository {
    suspend fun getAlbums(): List<AlbumDb>
}