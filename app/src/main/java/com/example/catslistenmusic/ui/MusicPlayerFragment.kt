package com.example.catslistenmusic.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.media3.common.MediaItem
import androidx.media3.common.PlaybackException
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import com.bumptech.glide.Glide
import com.example.catslistenmusic.R
import com.example.catslistenmusic.databinding.FragmentMusicPlayerBinding
import com.example.catslistenmusic.model.GigaTrack
import com.example.catslistenmusic.viewmodel.MusicPlayerViewModel
import com.example.catslistenmusic.viewmodel.utils.ViewModelFactory

class MusicPlayerFragment : Fragment() {
    private var _binding: FragmentMusicPlayerBinding? = null
    private val binding
        get() = _binding!!

    private lateinit var gigaTrack: GigaTrack

    private lateinit var musicPlayer: ExoPlayer

    private val viewModel: MusicPlayerViewModel by viewModels<MusicPlayerViewModel> {
        ViewModelFactory(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMusicPlayerBinding.inflate(layoutInflater, container, false)

        arguments?.let {
            gigaTrack = it.getParcelable(ARG)!!
        }

        binding.artist.text = gigaTrack.artistName
        binding.song.text = gigaTrack.title
        binding.album.text = gigaTrack.albumTitle
        Glide.with(requireContext())
            .load(gigaTrack.albumCover)
            .error(R.drawable.default_cover)
            .into(binding.albumCover)

        viewModel.playbackPosition.observe(viewLifecycleOwner) { position ->
            musicPlayer.seekTo(position)
        }

        musicPlayer = ExoPlayer.Builder(requireContext()).build()
        binding.musicPlayer.player = musicPlayer
        binding.musicPlayer.showTimeoutMs = 0
        musicPlayer.setMediaItem(MediaItem.fromUri(gigaTrack.preview))
        musicPlayer.addListener(object: Player.Listener {
            override fun onPlayerError(error: PlaybackException) {
                viewModel.setPlaybackPosition(musicPlayer.currentPosition)
                binding.musicGroup.isVisible = false
                binding.refreshGroup.isVisible = true
                super.onPlayerError(error)
            }
        })

        musicPlayer.prepare()
        musicPlayer.play()

        binding.refreshButton.setOnClickListener {
            musicPlayer.prepare()
            musicPlayer.play()
            binding.musicGroup.isVisible = true
            binding.refreshGroup.isVisible = false

        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        musicPlayer.release()
        _binding = null
    }

    override fun onStart() {
        super.onStart()
        musicPlayer.playWhenReady = true
    }

    override fun onStop() {
        super.onStop()
        viewModel.setPlaybackPosition(musicPlayer.currentPosition)
        musicPlayer.playWhenReady = false
    }

    companion object {
        const val ARG = "ARGUMENT"
        fun newInstance(gigaTrack: GigaTrack): MusicPlayerFragment {
            val args = Bundle().apply {
                putParcelable(ARG, gigaTrack)
            }
            val fragment = MusicPlayerFragment()
            fragment.arguments = args
            return fragment
        }
    }
}
