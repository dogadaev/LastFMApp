package org.dogadaev.lastfm.details.data.repository

import org.dogadaev.lastfm.db.dao.AlbumsDao
import org.dogadaev.lastfm.details.data.model.AlbumCommon
import org.dogadaev.lastfm.details.data.model.TrackCommon
import org.dogadaev.lastfm.net.api.API
import org.dogadaev.lastfm.net.data.model.getImageUrl
import org.dogadaev.lastfm.net.data.repository.NetworkChecker

class DetailsRepositoryImpl(
    private val api: API,
    private val apiKey: String,
    private val albumsDao: AlbumsDao,
    private val networkChecker: NetworkChecker
) : DetailsRepository {
    override suspend fun getAlbumDetails(artist: String, album: String): AlbumCommon {
        return if (networkChecker.isNetworkConnected) {
            val albumNet =
                api.getAlbumInfoAsync(apiKey, artist, album, mbid = null, lang = null, autoCorrect = null).albumInfo

            val tracks = albumNet.tracksResult.tracks.map { TrackCommon(it.name, it.duration) }
            AlbumCommon(albumNet.name, albumNet.artist, albumNet.images.getImageUrl(), tracks)
        } else {
            val albumDb = albumsDao.getAlbum(artist, album)

            if (albumDb == null) {
                AlbumCommon(album, artist, "", emptyList())
            } else {
                AlbumCommon(
                    albumDb.name,
                    albumDb.artist,
                    albumDb.imageUrl,
                    albumDb.tracksDb.map { TrackCommon(it.name, it.duration) }
                )
            }
        }
    }
}