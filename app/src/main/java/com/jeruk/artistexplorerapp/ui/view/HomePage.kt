package com.jeruk.artistexplorerapp.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.jeruk.artistexplorerapp.ui.viewmodel.ArtistArtistViewModel

@Composable
fun HomePage(
    modifier: Modifier = Modifier,
    viewModel: ArtistArtistViewModel = viewModel(),
    navController: NavController = rememberNavController()
) {
    val artist by viewModel.artist.collectAsState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFF282828))
    ) {
        Image(
            painter = rememberAsyncImagePainter(artist.coverUrl),
            contentDescription = "Cover",
            modifier = Modifier.size(200.dp)
        )
    }

}

@Composable
@Preview(showBackground = true, showSystemUi = true)
private fun HomePagePreview() {
    HomePage()
}