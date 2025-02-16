package com.example.catslistenmusic.model

import com.squareup.moshi.Json

data class TrackData(
    @Json(name = "data") val data: List<Track>
)