package com.example.catslistenmusic.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GigaTrack(
    val id: Long,
    val title: String,
    val preview: String,
    val artistName: String,
    val albumTitle: String,
    val albumCover: String,
    val nextTrack: String,
    val prevTrack: String
): Parcelable