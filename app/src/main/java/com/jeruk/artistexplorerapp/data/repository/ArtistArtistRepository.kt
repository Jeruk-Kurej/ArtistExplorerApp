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

    suspend fun AlbumAlbumAlbum(artistName: String): Album {
        val response = service.getAlbum(
            artistName = artistName
        )
        val body = response.body()!!
        val dtoAlbum = body.album.firstOrNull()!!

        return Album(
            nameAlbum = dtoAlbum.strAlbum,
            idAlbum = dtoAlbum.idAlbum.toInt(),
            releaseDate = dtoAlbum.intYearReleased,
            coverUrl = dtoAlbum.strAlbumThumb,
            artistId = dtoAlbum.idArtist.toInt()
        )
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