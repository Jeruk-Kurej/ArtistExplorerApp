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
            genre = dtoArtist.strGenre
        )
    }

    suspend fun AlbumAlbumAlbum(artistName: String): Album {
        val response = service.getAlbum(
            artistName = artistName
        )
        val body = response.body()!!
        val dtoArtist = body.album.firstOrNull()!!

        return Album(
            nameAlbum = dtoArtist.strAlbum,
            idAlbum = dtoArtist.idAlbum.toInt(),
            genre = dtoArtist.strGenre,
            descriptionAlbum = dtoArtist.strDescriptionEN,
            yearRelease = dtoArtist.intYearReleased.toInt()
        )
    }

    suspend fun TrackTrackTrack(albumId: Int): Track {
        val response = service.getTrack(
            albumId = albumId
        )
        val body = response.body()!!
        val dtoArtist = body.track.firstOrNull()!!

        return Track(
            nameTrack = dtoArtist.strTrack,
            idTrack = dtoArtist.idTrack.toInt(),
            duration = dtoArtist.intDuration.toInt()
        )
    }

}