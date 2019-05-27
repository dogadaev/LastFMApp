package org.dogadaev.lastfm.main.data.repository

import org.dogadaev.lastfm.db.dao.AlbumsDao
import org.dogadaev.lastfm.db.entity.AlbumDb

class AlbumsRepositoryImpl(
    private val albumsDao: AlbumsDao
) : AlbumsRepository {

    override suspend fun getAlbums(): List<AlbumDb> =
        albumsDao.getAllAlbums() ?: emptyList()
}