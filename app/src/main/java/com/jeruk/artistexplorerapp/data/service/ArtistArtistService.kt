package com.jeruk.panpanweatherapp.data.service

import com.jeruk.artistexplorerapp.data.dto.ResponseAlbum
import com.jeruk.artistexplorerapp.data.dto.ResponseArtist
import com.jeruk.artistexplorerapp.data.dto.ResponseTrack
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ArtistArtistService {

    @GET("search.php")
    suspend fun getArtistName(
        @Query("s") artistName: String,
    ): Response<ResponseArtist>

    @GET("searchalbum.php")
    suspend fun getAlbum(
        @Query("s") artistName: String,
    ): Response<ResponseAlbum>

    @GET("album.php")
    suspend fun getAlbumDetail(
        @Query("m") albumId: Int,
    ): Response<ResponseAlbum>

    @GET("track.php")
    suspend fun getTrack(
        @Query("m") albumId: Int,
    ): Response<ResponseTrack>

}