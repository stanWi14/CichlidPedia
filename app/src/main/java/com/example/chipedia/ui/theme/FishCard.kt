package com.example.chipedia.ui.theme


import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.example.chipedia.R
import com.example.chipedia.module.FishData


@Composable
fun FishCard(showImageUris: List<Uri>, submitedProp: FishData, location: String) {
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(colorBlue)
            .verticalScroll(rememberScrollState()) // Add vertical scroll here
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState())
        ) {
            showImageUris.forEachIndexed { index, uri ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Column {
                        Box(
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            // Image
                            Image(
                                painter = rememberImagePainter(
                                    data = uri,
                                    builder = {
                                        crossfade(true)
                                    }
                                ),
                                contentDescription = null,
                                modifier = Modifier
                                    .height(200.dp)
                                    .fillMaxWidth()
                                    .aspectRatio(1.9f), // Maintain image's original aspect ratio
                                contentScale = ContentScale.Crop
                            )
                        }
                    }
                }
            }
        }


        Text(
            text = submitedProp.marketName,
            style = MaterialTheme.typography.titleLarge.copy(
                fontSize = 40.sp
            ),
            color = Color.White,
            fontWeight = FontWeight.Bold,
            letterSpacing = 1.sp,
            modifier = Modifier.padding(horizontal = 5.dp)
        )

        Text(
            text = submitedProp.scieName,
            style = MaterialTheme.typography.titleLarge.copy(
                fontSize = 25.sp, fontStyle = FontStyle.Italic
            ),
            color = Color.White,
            letterSpacing = 1.sp,
            modifier = Modifier.padding(horizontal = 5.dp)
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "Maintenance",
            style = MaterialTheme.typography.titleLarge.copy(
                fontSize = 20.sp
            ),
            color = Color.White,
            fontWeight = FontWeight.Bold,
            letterSpacing = 1.sp,
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    width = 3.dp,
                    color = colorBlue,
                    shape = RoundedCornerShape(16.dp) // Adjust the corner radius as needed
                )
                .background(
                    color = colorRed,
                    shape = RoundedCornerShape(16.dp) // Same corner radius as the border
                )
                .padding(10.dp)
        )

        Column(
            modifier = Modifier.padding(horizontal = 5.dp)
        ) {

            TextIconFormat(
                painterResource(R.drawable.fish_food),
                "Diet",
                "Karnivora",
                " "
            )

            TextIconFormat(
                painterResource(R.drawable.max_size),
                "Ukuran Maksimum",
                "15",
                "cm"
            )

            TextIconFormat(
                painterResource(R.drawable.fish_lifespan),
                "Masa Hidup",
                "8",
                "Tahun"
            )

            TextIconFormat(
                painterResource(R.drawable.fish_locality),
                "Asal",
                location,
                ""
            )
        }

        Spacer(modifier = Modifier.height(10.dp))


        Text(
            text = "Tank Requirement",
            style = MaterialTheme.typography.titleLarge.copy(
                fontSize = 20.sp
            ),
            color = Color.White,
            fontWeight = FontWeight.Bold,
            letterSpacing = 1.sp,
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    width = 3.dp,
                    color = colorBlue,
                    shape = RoundedCornerShape(16.dp) // Adjust the corner radius as needed
                )
                .background(
                    color = colorRed,
                    shape = RoundedCornerShape(16.dp) // Same corner radius as the border
                )
                .padding(10.dp)
        )

        Column(
            modifier = Modifier.padding(horizontal = 5.dp)
        ) {
            TextIconFormat(
                painterResource(R.drawable.water_ph),
                "pH",
                "Pelet",
                " "
            )
            TextIconFormat(
                painterResource(R.drawable.water_temp),
                "Suhu",
                "20-30",
                context.getString(R.string.satCelcius)
            )

            TextIconFormat(
                painterResource(R.drawable.minimum_tank),
                "Minimum Tank",
                "Pelet",
                context.getString(R.string.satMKubik)
            )

            TextIconFormat(
                painterResource(R.drawable.water_flow),
                "Arus",
                "High",
                " "
            )

            TextIconFormat(
                painterResource(R.drawable.warning_aggresive),
                "Behaviour",
                "Aggresive",
                " "
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "Breeding",
            style = MaterialTheme.typography.titleLarge.copy(
                fontSize = 20.sp
            ),
            color = Color.White,
            fontWeight = FontWeight.Bold,
            letterSpacing = 1.sp,
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    width = 3.dp,
                    color = colorBlue,
                    shape = RoundedCornerShape(16.dp) // Adjust the corner radius as needed
                )
                .background(
                    color = colorRed,
                    shape = RoundedCornerShape(16.dp) // Same corner radius as the border
                )
                .padding(10.dp)
        )

        Column(
            modifier = Modifier.padding(horizontal = 5.dp)
        ) {
            TextIconFormat(
                painterResource(R.drawable.fish_sexing),
                "Sexing",
                "Male bigger than female",
                " "
            )
            TextIconFormat(
                painterResource(R.drawable.breeding_type),
                "Tipe Breeding",
                "Mouth Breder",
                " "
            )
        }
    }
}