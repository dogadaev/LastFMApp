package org.dogadaev.lastfm.albums.data.repository

import org.dogadaev.lastfm.albums.data.model.AlbumCommon
import org.dogadaev.lastfm.db.dao.AlbumsDao
import org.dogadaev.lastfm.db.entity.AlbumDb
import org.dogadaev.lastfm.db.entity.TrackDb
import org.dogadaev.lastfm.net.api.API
import org.dogadaev.lastfm.net.data.model.albums.TopAlbumsResult
import org.dogadaev.lastfm.statical.media.ImageLoader

class AlbumsRepositoryImpl(
    private val api: API,
    private val apiKey: String,
    private val albumsDao: AlbumsDao,
    private val imageLoader: ImageLoader
) : AlbumsRepository {

    override suspend fun getTopAlbums(artist: String, page: Int?, limit: Int?): TopAlbumsResult {
        return getTopAlbumsByArtist(artist, page, limit)
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
        imageLoader.cache(albumDb.imageUrl)
    }

    override suspend fun deleteAlbum(album: AlbumDb) {
        albumsDao.delete(album)
    }

    override suspend fun deleteAlbum(artist: String, album: String) {
        albumsDao.delete(artist, album)
    }

    private suspend fun getTopAlbumsByArtist(artist: String, page: Int?, limit: Int?): TopAlbumsResult =
        api.getTopAlbumsAsync(apiKey, artist = artist, mbid = null, page = page, limit = limit)
}