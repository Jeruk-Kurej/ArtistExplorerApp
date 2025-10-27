package com.jeruk.artistexplorerapp.data.container

import com.google.gson.GsonBuilder
import com.jeruk.artistexplorerapp.data.repository.ArtistArtistRepository
import com.jeruk.panpanweatherapp.data.service.ArtistArtistService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ArtistArtistContainer {

    companion object {
        private const val BASE_URL = "https://www.theaudiodb.com/api/v1/json/123/"
    }

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
        .build()

    private val artistArtistService: ArtistArtistService by lazy {
        retrofit.create(ArtistArtistService::class.java)
    }

    val artistArtistRepository: ArtistArtistRepository by lazy {
        ArtistArtistRepository(artistArtistService)
    }
}