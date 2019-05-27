package org.dogadaev.lastfm.albums.data.repository

import org.dogadaev.lastfm.albums.data.model.AlbumCommon
import org.dogadaev.lastfm.db.entity.AlbumDb
import org.dogadaev.lastfm.net.data.model.albums.TopAlbumsResult

interface AlbumsRepository {
    suspend fun getTopAlbums(artist: String, mbid: String?, page: Int? = null, limit: Int? = null): TopAlbumsResult
    suspend fun getSavedAlbums(artist: String): List<AlbumDb>
    suspend fun saveAlbum(album: AlbumCommon)
    suspend fun deleteAlbum(album: AlbumDb)
    suspend fun deleteAlbum(artist: String, album: String)
}