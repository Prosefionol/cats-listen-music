package com.example.catslistenmusic.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.catslistenmusic.Navigator
import com.example.catslistenmusic.R
import com.example.catslistenmusic.databinding.ItemViewBinding
import com.example.catslistenmusic.model.Track

class TrackAdapter(
    private val context: Context
): RecyclerView.Adapter<TrackAdapter.ViewHolder>() {

    private var tracks: List<Track> = emptyList()

    inner class ViewHolder(private val binding: ItemViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(track: Track) {
            if (track.title.isEmpty()) {
                binding.rvSong.text = context.getString(R.string.unknown_song)
            } else {
                binding.rvSong.text = track.title
            }
            if (track.artist.name.isEmpty()) {
                binding.rvArtist.text = context.getString(R.string.unknown_artist)
            } else {
                binding.rvArtist.text = track.artist.name
            }
            Glide.with(context)
                .load(track.album.cover)
                .error(R.drawable.default_cover)
                .into(binding.rvImage)

            binding.root.setOnClickListener {
                if (context is Navigator) {
                    context.listenTrack(track)
                }
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemViewBinding.inflate((LayoutInflater.from(parent.context)), parent, false)
        return ViewHolder(binding)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(tracks[position])
    }
    override fun getItemCount(): Int = tracks.size

    fun submitList(newTracks: List<Track>) {
        val diffResult = DiffUtil.calculateDiff(TrackDiffCallback(tracks, newTracks))
        tracks = newTracks
        diffResult.dispatchUpdatesTo(this)
    }
}
