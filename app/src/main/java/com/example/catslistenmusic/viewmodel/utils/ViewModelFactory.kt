package com.example.catslistenmusic.viewmodel.utils

import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import com.example.catslistenmusic.viewmodel.MusicPlayerViewModel
import com.example.catslistenmusic.viewmodel.OnlineMusicViewModel

class ViewModelFactory(
    owner: SavedStateRegistryOwner
): AbstractSavedStateViewModelFactory(owner, null) {
    override fun <T : ViewModel> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ): T {
        val viewModel = when(modelClass) {
            OnlineMusicViewModel::class.java -> {
                OnlineMusicViewModel(handle)
            }
            MusicPlayerViewModel::class.java -> {
                MusicPlayerViewModel(handle)
            }
            else -> throw IllegalStateException()
        }
        return viewModel as T
    }
}
