package com.jeruk.artistexplorerapp.data.repository

import com.jeruk.artistexplorerapp.ui.model.Track
import com.jeruk.artistexplorerapp.ui.model.Album
import com.jeruk.artistexplorerapp.ui.model.Artist
import com.jeruk.panpanweatherapp.data.service.ArtistArtistService

class ArtistArtistRepository(private val service: ArtistArtistService) {

    suspend fun ArtistArtistArtist(artistName: String): Artist {
        val response = service.getArtistName(
            artistName = artistName
        )
        val body = response.body()!!
        val dtoArtist = body.artists.firstOrNull()!!

        return Artist(
            nameArtist = dtoArtist.strArtist,
            idArtist = dtoArtist.idArtist.toInt(),
            genre = dtoArtist.strGenre,
            coverUrl = dtoArtist.strArtistThumb
        )
    }

    suspend fun AlbumAlbumAlbum(artistName: String): List<Album> {
        val response = service.getAlbum(
            artistName = artistName
        )
        val body = response.body()!!
        val dtoAlbum = body.album ?: emptyList()

        return dtoAlbum
            .filter { !it.strAlbumThumb.isNullOrBlank() && !it.strAlbum.isNullOrBlank() }
            .map {
                Album(
                    nameAlbum = it.strAlbum,
                    idAlbum = it.idAlbum.toIntOrNull() ?: -1,
                    releaseDate = it.intYearReleased ?: "Unknown",
                    coverUrl = it.strAlbumThumb ?: "",
                    artistId = it.idArtist.toIntOrNull() ?: 0,
                    genre = it.strGenre ?: "Unknown"
                )
            }


    }

    suspend fun TrackTrackTrack(albumId: Int): List<Track> {
        val response = service.getTrack(
            albumId = albumId
        )
        val body = response.body()!!
        val dtoTrack = body.track ?: emptyList()

        return dtoTrack.map {
            Track(
                nameTrack = it.strTrack,
                idTrack = it.idTrack.toInt(),
                duration = it.intDuration.toInt()
            )
        }
    }
}