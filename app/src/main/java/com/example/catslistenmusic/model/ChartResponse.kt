package com.example.catslistenmusic.model

import com.squareup.moshi.Json

data class ChartResponse(
    @Json(name = "tracks") val tracks: TrackData
)