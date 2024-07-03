package com.example.chipedia.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.chipedia.R

@Composable
fun TextIconFormat(icon: Painter, text1: String, text2: String, text3: String) {
    Row(
        modifier = Modifier.padding(5.dp),
        verticalAlignment = Alignment.CenterVertically

    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .background(
                    color = Color.White,
                    shape = CircleShape
                )
                .border(
                    width = 2.dp,
                    color = colorRed,
                    shape = CircleShape
                )
        ) {
            Icon(
                modifier = Modifier
                    .padding(4.dp),
                painter = icon,
                contentDescription = "Icon"
            )
        }
        Spacer(modifier = Modifier.width(5.dp))
        Text(
            text1,
            color = Color.White,
            letterSpacing = 1.sp,
        ) // Darken text if selected
        Spacer(modifier = Modifier.width(5.dp))
        Text(
            text2,
            color = Color.White,
            letterSpacing = 1.sp
        ) // Darken text if selected        Spacer(modifier = Modifier.width(5.dp))
        Spacer(modifier = Modifier.width(5.dp))
        Text(
            text3,
            color = Color.White,
            letterSpacing = 1.sp,
        ) // Darken text if selected
    }
}

@Preview
@Composable
fun SimpleComposablePreview() {
    TextIconFormat(painterResource(R.drawable.fish_food), "diet", "Pelet", " ")
}