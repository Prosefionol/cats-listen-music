package com.example.catslistenmusic

import androidx.fragment.app.Fragment
import com.example.catslistenmusic.model.Track

interface Navigator {
    fun loadFragment (fragment: Fragment)
    fun listenTrack (track: Track)
}