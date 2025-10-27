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

    fun loadArtistData(artistName: String, albumId: Int) {
        viewModelScope.launch {
            try {
                val repository = ArtistArtistContainer().artistArtistRepository

                val artistResult = repository.ArtistArtistArtist(artistName)
                val albumResult = repository.AlbumAlbumAlbum(artistName)
                val trackResults = repository.TrackTrackTrack(albumId)

                _artist.value = artistResult.copy(
                    isError = false, errorMessage = null
                )
                _album.value = albumResult.copy(
                    isError = false, errorMessage = null
                )
                _tracks.value = trackResults.map {
                    it.copy(
                        isError = false, errorMessage = null
                    )
                }

            } catch (e: Exception) {
                _artist.value = _artist.value.copy(
                    isError = true, errorMessage = "Failed to load artist"
                )
                _album.value = _album.value.copy(
                    isError = true, errorMessage = "Failed to load album"
                )
                _tracks.value = listOf(
                    Track(
                        isError = true, errorMessage = "Failed to load tracks"
                    )
                )
            }
        }
    }
}
