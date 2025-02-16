package com.example.catslistenmusic.model

import com.squareup.moshi.Json

data class Artist(
    @Json(name = "name") val name: String
)