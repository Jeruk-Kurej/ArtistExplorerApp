package com.jeruk.artistexplorerapp.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.jeruk.artistexplorerapp.ui.model.Album

@Composable
fun AlbumCard(
    album: Album,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults
            .cardColors(
                containerColor = Color(0xFF1E1E1E)
            )
    ) {
        Column(
            horizontalAlignment = Alignment.Start,
        ) {
            AsyncImage(
                model = album.coverUrl,
                contentDescription = "album cover",
                modifier = Modifier
                    .height(150.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .size(160.dp),
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = modifier
                    .padding(start = 12.dp, bottom = 12.dp, top = 12.dp)
            ) {
                Text(
                    text = album.nameAlbum,
                    fontSize = 14.sp,
                    color = Color(0xFFA6A07A),
                    maxLines = 1
                )
                Text(
                    text = "${album.releaseDate} • ${album.genre}",
                    fontSize = 12.sp,
                    color = Color(0xFFA6A07A)
                )
            }
        }
    }
}

