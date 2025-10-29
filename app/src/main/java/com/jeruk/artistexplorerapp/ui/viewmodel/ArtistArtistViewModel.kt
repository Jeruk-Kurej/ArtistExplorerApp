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
import java.io.IOException

class ArtistArtistViewModel : ViewModel() {

    private val _artist = MutableStateFlow(Artist())
    val artist: StateFlow<Artist> = _artist

    private val _albums = MutableStateFlow<List<Album>>(emptyList())
    val albums: StateFlow<List<Album>> = _albums

    private val _tracks = MutableStateFlow<List<Track>>(emptyList())
    val tracks: StateFlow<List<Track>> = _tracks

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val repository = ArtistArtistContainer().artistArtistRepository

    fun loadArtist(artistName: String) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val artistResult = repository.ArtistArtistArtist(artistName)
                _artist.value = artistResult
                loadAlbums(artistName)
            } catch (e: IOException) {
                _artist.value = Artist(
                    isError = true,
                    errorMessage = "Tidak ada koneksi internet."
                )
            } catch (e: Exception) {
                _artist.value = Artist(
                    isError = true,
                    errorMessage = e.message ?: "Gagal memuat data artist."
                )
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun loadAlbums(artistName: String) {
        viewModelScope.launch {
            try {
                val albumResults = repository.AlbumAlbumAlbum(artistName)
                _albums.value = albumResults
            } catch (e: IOException) {
                _albums.value = emptyList()
            } catch (e: Exception) {
                _albums.value = emptyList()
            }
        }
    }
    fun loadTracks(albumId: Int) {
        viewModelScope.launch {
            try {
                val trackResults = repository.TrackTrackTrack(albumId)
                _tracks.value = trackResults
            } catch (e: IOException) {
                _tracks.value = listOf(
                    Track(isError = true, errorMessage = "Tidak ada koneksi internet.")
                )
            } catch (e: Exception) {
                _tracks.value = listOf(
                    Track(isError = true, errorMessage = e.message ?: "Gagal memuat lagu.")
                )
            }
        }
    }
}
