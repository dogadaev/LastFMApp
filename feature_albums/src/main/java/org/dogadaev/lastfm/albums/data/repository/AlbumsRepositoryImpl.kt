package org.dogadaev.lastfm.albums.data.repository

import org.dogadaev.lastfm.albums.data.model.AlbumCommon
import org.dogadaev.lastfm.db.dao.AlbumsDao
import org.dogadaev.lastfm.db.entity.AlbumDb
import org.dogadaev.lastfm.db.entity.TrackDb
import org.dogadaev.lastfm.net.api.API
import org.dogadaev.lastfm.net.data.model.albums.TopAlbumsResult

class AlbumsRepositoryImpl(
    private val api: API,
    private val apiKey: String,
    private val albumsDao: AlbumsDao
) : AlbumsRepository {

    override suspend fun getTopAlbums(artist: String, mbid: String?, page: Int?, limit: Int?): TopAlbumsResult {
        return if (mbid == null) getTopAlbumsByArtist(artist, page, limit)
        else getTopAlbumsById(mbid, page, limit)
    }

    override suspend fun getSavedAlbums(artist: String): List<AlbumDb> {
        return albumsDao.getAlbums(artist) ?: emptyList()
    }

    override suspend fun saveAlbum(album: AlbumCommon) {
        val albumInfoResult =
            api.getAlbumInfoAsync(apiKey, album.artist.name, album.name, mbid = null, lang = null, autoCorrect = null)

        val tracks = albumInfoResult.albumInfo.tracksResult.tracks.map { TrackDb(it.name, it.duration) }
        val albumDb = AlbumDb(
            album.name,
            album.artist.name,
            album.imageUrl,
            tracks
        )

        albumsDao.insert(albumDb)
    }

    override suspend fun deleteAlbum(album: AlbumDb) {
        albumsDao.delete(album)
    }

    override suspend fun deleteAlbum(artist: String, album: String) {
        albumsDao.delete(artist, album)
    }

    private suspend fun getTopAlbumsByArtist(artist: String, page: Int?, limit: Int?): TopAlbumsResult =
        api.getTopAlbumsAsync(apiKey, artist = artist, mbid = null, page = page, limit = limit)

    private suspend fun getTopAlbumsById(mbid: String, page: Int?, limit: Int?): TopAlbumsResult =
        api.getTopAlbumsAsync(apiKey, artist = null, mbid = mbid, page = page, limit = limit)
}