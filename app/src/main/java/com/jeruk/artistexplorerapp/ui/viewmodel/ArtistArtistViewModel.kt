package com.jeruk.artistexplorerapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jeruk.artistexplorerapp.data.container.ArtistArtistContainer
import com.jeruk.artistexplorerapp.ui.model.Artist
import com.jeruk.artistexplorerapp.ui.model.Album
import com.jeruk.artistexplorerapp.ui.model.Track
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ArtistArtistViewModel : ViewModel() {

    private val _artist = MutableStateFlow(Artist())
    val artist: StateFlow<Artist> = _artist

    private val _album = MutableStateFlow(Album())
    val album: StateFlow<Album> = _album

    private val _tracks = MutableStateFlow<List<Track>>(emptyList())
    val tracks: StateFlow<List<Track>> = _tracks

    val repository = ArtistArtistContainer().artistArtistRepository

    fun loadArtist(artistName: String = "John Mayer") {
        viewModelScope.launch {
            try {
                val artistResult = repository.ArtistArtistArtist(artistName)
                _artist.value = artistResult
                loadAlbums(artistName)
            } catch (e: Exception) {
                _artist.value = Artist(
                    isError = true,
                    errorMessage = e.message ?: "Failed to load artist"
                )
            }
        }
    }

    fun loadAlbums(artistName: String) {
        viewModelScope.launch {
            try {
                val albumResult = repository.AlbumAlbumAlbum(artistName)
                _album.value = albumResult
                loadTracks(albumResult.idAlbum)
            } catch (e: Exception) {
                _album.value = Album(
                    isError = true,
                    errorMessage = e.message ?: "Failed to load album"
                )
            }
        }
    }

    fun loadTracks(albumId: Int) {
        viewModelScope.launch {
            try {
                val trackResults = repository.TrackTrackTrack(albumId)
                _tracks.value = trackResults
            } catch (e: Exception) {
                _tracks.value = listOf(
                    Track(
                        isError = true,
                        errorMessage = e.message ?: "Failed to load tracks"
                    )
                )
            }
        }
    }
}
