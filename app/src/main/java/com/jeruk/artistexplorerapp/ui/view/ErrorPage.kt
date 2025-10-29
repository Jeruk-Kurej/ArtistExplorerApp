package com.jeruk.artistexplorerapp.ui.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ErrorPage(
    message: String = "Terjadi kesalahan. Periksa koneksi internet Anda.",
    onRetry: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF282828))
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Error",
            color = Color(0xFFA6A07A),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = message,
            color = Color(0xFFE57373),
            fontSize = 15.sp,
            modifier = Modifier.padding(top = 12.dp, bottom = 20.dp)
        )

        if (onRetry != null) {
            Button(
                onClick = onRetry,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFA6A07A),
                    contentColor = Color.Black
                )
            ) {
                Text("Coba Lagi", fontWeight = FontWeight.SemiBold)
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun ErrorPagePreview() {
    ErrorPage("Tidak ada koneksi internet.")
}
