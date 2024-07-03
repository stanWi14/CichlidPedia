package com.example.chipedia.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.chipedia.R

@Composable
fun AboutUsScreen() {
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorBlue)
            .verticalScroll(rememberScrollState()), // Add vertical scroll here,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Cerita CHI",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
        )
        Text(
            text = context.getString(R.string.onBoardingText1), color = Color.White,
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Visi & Misi CHIPedia",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
        )
        Text(
            text = context.getString(R.string.onBoardingText1), color = Color.White,
        )
        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Bergabung dengan kami",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
        )
        Text(
            text = context.getString(R.string.onBoardingSubTitle1), color = Color.White,
        )
        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Credits", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color.White,
        )
        DevCreditCard("Stanley Wisely", "App Developer", "stanleywisely257@gmail.com")
        DevCreditCard("Josse Christopher", "UI Consultant", "email123@gmail.com")
        DevCreditCard("Sam", "Tester", "email123@gmail.com")

    }
}

@Composable
fun DevCreditCard(nama: String, role: String, contact: String) {
    Column(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .border(
                width = 2.dp,
                color = colorRed,
                shape = CircleShape
            )
            .padding(10.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = nama, fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color.White,
        )
        Text(
            text = role, color = Color.White,
        )
        Text(
            text = contact, color = Color.White,
        )
    }
}