import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card        // Handle any errors

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.storage
import kotlinx.coroutines.tasks.await

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListScreen(value: Int, navController: NavHostController) {
    val propertyList =
        remember { mutableStateOf<List<Triple<String, String, String>>>(emptyList()) }

    val collectionFish: String = stringResource(value)
    LaunchedEffect(value) {
        loadData(collectionFish, propertyList)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = collectionFish,
                        color = Color.White,
                    )
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = com.example.chipedia.ui.theme.colorRed),
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                }
            )
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(com.example.chipedia.ui.theme.colorBlue)
            ) {
                Spacer(modifier = Modifier.height(60.dp))
                LazyColumn(modifier = Modifier.weight(1f)) {
                    items(propertyList.value) { (marketName, sciName, imageUrl) ->
                        PropertyCard(
                            marketName = marketName,
                            sciName = sciName,
                            imageUrl = imageUrl
                        )
                    }
                }
            }
        }
    )
}

@Composable
fun PropertyCard(marketName: String, sciName: String, imageUrl: String) {
    Card(
        modifier = Modifier.padding(8.dp)
    ) {
        val gradientRedWhite = Brush.verticalGradient(
            0f to com.example.chipedia.ui.theme.colorTransparent,
            500f to com.example.chipedia.ui.theme.coloeBlack
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Image(
                painter = rememberImagePainter(imageUrl),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(175.dp),
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        gradientRedWhite
                    )
                    .padding(8.dp)
                    .align(Alignment.BottomStart) // Align the column to the bottom
            ) {
                Text(
                    text = marketName,
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontSize = 30.sp
                    ),
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.sp,
                )
                Text(
                    text = sciName,
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontSize = 15.sp,
                        fontStyle = FontStyle.Italic
                    ),
                    color = Color.White,
//                    modifier = Modifier
//                        .padding(top = 5.dp),
                    letterSpacing = 1.sp,
                )
            }
        }
    }
}

private suspend fun loadData(
    value: String,
    propertyList: MutableState<List<Triple<String, String, String>>>
) {
    val db = FirebaseFirestore.getInstance()
    val propertyCollection = db.collection(value)

    try {
        val documents = propertyCollection.get().await()
        val properties = mutableListOf<Triple<String, String, String>>()
        for (document in documents) {
            val sciName = document.getString("ScieName") ?: "N/A"
            val marketName = document.getString("MarketName") ?: "N/A"
            val propID = document.id
            val imageUrl = getFirstImageURL(propID)
            properties.add(Triple(marketName, sciName, imageUrl))
        }
        propertyList.value = properties
    } catch (e: Exception) {
        // Handle failure
        e.printStackTrace()
    }
}

private suspend fun getFirstImageURL(propID: String): String {
    return try {
        val storageReference = Firebase.storage.reference.child("$propID/1.jpg")
        storageReference.downloadUrl.await().toString()
    } catch (e: Exception) {
        // Handle failure
        e.printStackTrace()
        ""
    }
}
