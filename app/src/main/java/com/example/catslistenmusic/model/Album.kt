package com.example.catslistenmusic.model

import com.squareup.moshi.Json

data class Album(
    @Json(name = "title") val title: String,
    @Json(name = "cover") val cover: String
)