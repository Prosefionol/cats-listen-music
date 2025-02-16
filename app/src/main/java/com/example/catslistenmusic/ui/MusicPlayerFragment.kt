package com.example.catslistenmusic.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.catslistenmusic.databinding.FragmentMusicPlayerBinding
import com.example.catslistenmusic.model.GigaTrack

class MusicPlayerFragment : Fragment() {
    private var _binding: FragmentMusicPlayerBinding? = null
    private val binding
        get() = _binding!!

    private lateinit var gigaTrack: GigaTrack

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
        binding.placeholder.text = gigaTrack.preview

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
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
