package com.example.catslistenmusic.ui.adapters

import androidx.recyclerview.widget.DiffUtil
import com.example.catslistenmusic.model.Track

class TrackDiffCallback(
    private val oldList: List<Track>,
    private val newList: List<Track>
): DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size
    override fun getNewListSize(): Int = newList.size
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }
    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}
