package com.example.catslistenmusic.model

import com.squareup.moshi.Json

data class Track(
    @Json(name = "id") val id: Long,
    @Json(name = "title") val title: String,
    @Json(name = "preview") val preview: String,
    @Json(name = "artist") val artist: Artist,
    @Json(name = "album") val album: Album
)

fun Track.toGigaTrack(nextTrack: String = "", prevTrack: String = ""): GigaTrack {
    return GigaTrack(
        id = this.id,
        title = this.title,
        preview = this.preview,
        artistName = this.artist.name,
        albumCover = this.album.cover,
        albumTitle = this.album.title,
        nextTrack = nextTrack,
        prevTrack = prevTrack
    )
}