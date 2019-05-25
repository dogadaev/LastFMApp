package org.dogadaev.lastfm.net.api

import org.dogadaev.lastfm.net.data.model.search.ArtistSearchResult
import org.dogadaev.lastfm.net.data.model.albums.TopAlbumsResult
import retrofit2.http.GET
import retrofit2.http.Query

interface API {

    /**
     * Search for an artist by name.
     *
     * @param apiKey - A Last.fm API key.
     * @param artist - The artist name.
     * @param page - (Optional) The page number to fetch. Defaults to first page.
     * @param limit - (Optional) he number of results to fetch per page. Defaults to 30.
     *
     * @return AlbumsArtist matches sorted by relevance
     */
    @GET("/2.0/?method=artist.search&format=json")
    suspend fun searchForArtistAsync(
        @Query("api_key") apiKey: String,
        @Query("artist") artist: String,
        @Query("page") page: Int?,
        @Query("limit ") limit: Int?
    ): ArtistSearchResult

    /**
     * Get the top albums for an artist on Last.fm, ordered by popularity.
     *
     * @param apiKey - A Last.fm API key.
     * @param artist - The artist name.
     * @param mbid - The musicbrainz id for the album.
     * @param page - (Optional) The page number to fetch. Defaults to first page.
     * @param limit - (Optional) he number of results to fetch per page. Defaults to 30.
     *
     * @return The top albums for an artist.
     */
    @GET("/2.0/?method=artist.gettopalbums&format=json")
    suspend fun getTopAlbumsAsync(
        @Query("api_key") apiKey: String,
        @Query("artist") artist: String?,
        @Query("mbid") mbid: String,
        @Query("page") page: Int?,
        @Query("limit ") limit: Int?
    ): TopAlbumsResult

    /**
     * Get the metadata and tracklist for an album on Last.fm using the album name or a musicbrainz id.
     *
     * @param apiKey - A Last.fm API key.
     * @param artist - (unless mbid) The artist name.
     * @param album - (unless mbid) The album name.
     * @param mbid - (Optional) The musicbrainz id for the album.
     * @param lang - (Optional) The language to return the biography in, expressed as an ISO 639 alpha-2 code.
     * @param autoCorrect - (Optional) [0|1] Transform misspelled artist names into correct artist names,
     * returning the correct version instead. The corrected artist name will be returned in the response.
     *
     * @return The metadata and tracklist for an album.
     */
    @GET("/2.0/?method=album.getinfo&format=json")
    suspend fun getAlbumInfoAsync(
        @Query("api_key") apiKey: String,
        @Query("artist") artist: String?,
        @Query("album") album: String?,
        @Query("mbid") mbid: String?,
        @Query("lang") lang: String?,
        @Query("autocorrect") autoCorrect: Int?
    )
}