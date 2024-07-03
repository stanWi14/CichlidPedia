package com.example.chipedia.module

data class Document(
    val colID: String,
    val docID: String,
    val marketName: String,
    val sciName: String,
    val imageUrl: String
)

data class FishData(
    val scieName: String,
    val marketName: String,
    val maxSize: String,
    val lifeSpan: String,
    val aggresion: String,
    val breeding: String,
    val description: String
)
