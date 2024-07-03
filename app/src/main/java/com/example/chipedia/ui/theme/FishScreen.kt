package com.example.chipedia.ui.theme

import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.example.chipedia.module.FishData
import com.google.android.gms.tasks.Tasks
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage

@Composable
fun FishScreen(navController: NavHostController, value: Int, docID: String) {
    var fishData by remember { mutableStateOf<FishData?>(null) }
    var imageUris by remember { mutableStateOf<List<Uri>>(emptyList()) }
    val collectionFish: String = stringResource(value)
    val context = LocalContext.current

    LaunchedEffect(docID) {
        readFishData(collectionFish, docID,
            onSuccess = { data, uris ->
                fishData = data
                imageUris = uris
            },
            onFailure = { exception ->
                Toast.makeText(
                    context,
                    "Failed to load data: ${exception.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        )
    }

    if (fishData != null) {
        FishCard(showImageUris = imageUris, submitedProp = fishData!!, collectionFish)
    } else {
        Column(
            modifier = Modifier.background(colorBlue),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Loading...")
            Text(collectionFish)
        }
    }
}

fun readFishData(
    colId: String,
    docId: String,
    onSuccess: (FishData, List<Uri>) -> Unit,
    onFailure: (Exception) -> Unit
) {
    val firestore = FirebaseFirestore.getInstance()
    val storageRef = FirebaseStorage.getInstance().reference

    firestore.collection(colId).document(docId).get()
        .addOnSuccessListener { document ->
            if (document != null) {
                val fishData = FishData(
                    scieName = document.getString("ScieName") ?: "",
                    marketName = document.getString("MarketName") ?: "",
                    maxSize = document.getString("MaxSize") ?: "",
                    lifeSpan = document.getString("LifeSpan") ?: "",
                    aggresion = document.getString("Aggresion") ?: "",
                    breeding = document.getString("Breeding") ?: "",
                    description = document.getString("Description") ?: ""
                )

                val imageUris = mutableListOf<Uri>()
                val imagesFolderRef = storageRef.child(docId)

                imagesFolderRef.listAll().addOnSuccessListener { listResult ->
                    val imageTasks = listResult.items.map { imageRef ->
                        imageRef.downloadUrl.addOnSuccessListener { uri ->
                            imageUris.add(uri)
                        }
                    }

                    Tasks.whenAllSuccess<Uri>(imageTasks).addOnSuccessListener {
                        onSuccess(fishData, imageUris)
                    }.addOnFailureListener { exception ->
                        onFailure(exception)
                    }
                }.addOnFailureListener { exception ->
                    onFailure(exception)
                }
            } else {
                onFailure(Exception("No document found"))
            }
        }
        .addOnFailureListener { exception ->
            onFailure(exception)
        }
}

