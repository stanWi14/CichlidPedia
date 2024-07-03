package com.example.chipedia.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun NewsScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
    ) {
        Text(text = "Coming Events", fontSize = 24.sp, fontWeight = FontWeight.Bold)
        // carousel of images
        Text(text = "Our Last Event", fontSize = 24.sp, fontWeight = FontWeight.Bold)
        // carousel of images
        Text(text = "Event Documentation", fontSize = 24.sp, fontWeight = FontWeight.Bold)
        // carousel of images
    }
}