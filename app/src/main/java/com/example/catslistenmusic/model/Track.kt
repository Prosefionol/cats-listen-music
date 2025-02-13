package com.example.catslistenmusic.model

import com.squareup.moshi.Json

data class Track(
    @Json(name = "id") val id: Long,
    @Json(name = "title") val title: String,
    @Json(name = "preview") val preview: String,
    @Json(name = "artist") val artist: Artist,
    @Json(name = "album") val album: Album
)