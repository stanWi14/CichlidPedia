package com.example.chipedia.ui.theme

import android.app.ProgressDialog
import android.content.Context
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage

val locationList = arrayOf(
    "Tanganyika",
    "Malawi",
    "Victoria",
    "West Africa",
    "Madagascar",
    "Central America",
    "South America",
    "India"
)

fun generateDocIdAndPushField(
    imageUris: List<Uri>,
    scieName: String,
    marketName: String,
    maxSize: String,
    lifeSpan: String,
    aggresion: String,
    breeding: String,
    location: String,
    desc: String,
    navigateBack: () -> Unit,
    context: Context
) {
    var selectedLocation = location
    if (selectedLocation.isEmpty()) {
        selectedLocation = locationList[0]
    }
    val fishData = hashMapOf(
        "ScieName" to scieName,
        "MarketName" to marketName,
        "MaxSize" to maxSize,
        "LifeSpan" to lifeSpan,
        "Aggresion" to aggresion,
        "Breeding" to breeding,
        "Description" to desc
    )
    val firestore = FirebaseFirestore.getInstance()

    val progressDialog = ProgressDialog(context)
    progressDialog.setMessage("Uploading...")
    progressDialog.setCancelable(false)
    progressDialog.show()

    firestore.collection(selectedLocation).add(fishData).addOnSuccessListener { documentReference ->
        val docId = documentReference.id
        var uploadCount = 0
        imageUris.forEachIndexed { index, uri ->
            uploadImageToFirebaseStorage(uri, docId, index) {
                uploadCount++
                if (uploadCount == imageUris.size) {
                    progressDialog.dismiss()
                    Toast.makeText(context, "$scieName Data Uploaded", Toast.LENGTH_SHORT).show()
                    navigateBack()
                }
            }
        }
    }
}

fun uploadImageToFirebaseStorage(imageUri: Uri, docId: String, index: Int, onComplete: () -> Unit) {
    val storageRef = FirebaseStorage.getInstance().reference
    val imageName = "${index + 1}.jpg"
    val imagesRef = storageRef.child("$docId/$imageName")
    imagesRef.putFile(imageUri).addOnSuccessListener {
        imagesRef.downloadUrl.addOnSuccessListener { uri ->
            onComplete()
        }
    }.addOnFailureListener { exception ->
        // Handle any errors
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddFish(navController: NavHostController) {

    var selectedImageUris by remember { mutableStateOf<List<Uri>>(emptyList()) }
    var docId by remember { mutableStateOf("") }
    var scienName by remember { mutableStateOf("") }
    var marketName by remember { mutableStateOf("") }
    var maxSize by remember { mutableStateOf("") }
    var lifeSpan by remember { mutableStateOf("") }
    var location: String by remember { mutableStateOf("") }
    var teritorial by remember { mutableStateOf(true) }
    var aggresion: String by remember { mutableStateOf("") }
    var breeding by remember { mutableStateOf("") }
    var desc by remember { mutableStateOf("") }

    val context = LocalContext.current
    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf(locationList[0]) }

    val launcher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) { uri ->
            uri?.let {
                val updatedUris = selectedImageUris.toMutableList()
                updatedUris.add(uri)
                selectedImageUris = updatedUris
            }
        }

    val navigateBackFunction: () -> Unit = {
        navController.popBackStack()
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(onClick = { launcher.launch("image/*") }) {
                    Text(text = "Select Image")
                }

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier
                        .horizontalScroll(rememberScrollState())
                        .fillMaxWidth()
                ) {
                    selectedImageUris.forEach { uri ->
                        Image(
                            painter = rememberImagePainter(data = uri, builder = {
                                crossfade(true)
                            }),
                            contentDescription = null,
                            modifier = Modifier
                                .size(200.dp)
                                .padding(horizontal = 4.dp),
                            contentScale = ContentScale.Crop
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))
                Spacer(modifier = Modifier.height(16.dp))

                TextField(
                    value = scienName,
                    onValueChange = { scienName = it },
                    label = { Text("Enter Fish Name") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))

                TextField(
                    value = marketName,
                    onValueChange = { marketName = it },
                    label = { Text("Enter Market Name") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    ExposedDropdownMenuBox(expanded = expanded, onExpandedChange = {
                        expanded = !expanded
                    }) {
                        TextField(
                            value = selectedText,
                            onValueChange = {},
                            readOnly = true,
                            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                            modifier = Modifier
                                .menuAnchor()
                                .fillMaxWidth()
                        )

                        ExposedDropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { expanded = false }) {
                            locationList.forEach { item ->
                                DropdownMenuItem(text = { Text(text = item) }, onClick = {
                                    selectedText = item
                                    location = item
                                    expanded = false
                                })
                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))

                TextField(
                    value = maxSize,
                    onValueChange = { maxSize = it },
                    label = { Text("Enter Max Size") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))

                TextField(
                    value = lifeSpan,
                    onValueChange = { lifeSpan = it },
                    label = { Text("Enter Life Span") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))

                TextField(
                    value = aggresion,
                    onValueChange = { aggresion = it },
                    label = { Text("Enter Aggression") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))

                TextField(
                    value = breeding,
                    onValueChange = { breeding = it },
                    label = { Text("Enter Breeding Type") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))

                TextField(
                    value = desc,
                    onValueChange = { desc = it },
                    label = { Text("Enter Description") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))

                Button(onClick = {
                    generateDocIdAndPushField(
                        selectedImageUris,
                        scienName,
                        marketName,
                        maxSize,
                        lifeSpan,
                        aggresion,
                        breeding,
                        location,
                        desc,
                        navigateBackFunction,
                        context
                    )
                }) {
                    Text(text = "Upload")
                }
            }
        }
    }
}
