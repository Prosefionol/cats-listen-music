package com.example.catslistenmusic.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.example.catslistenmusic.databinding.FragmentLocalMusicBinding

class LocalMusicFragment : Fragment() {
    private var _binding: FragmentLocalMusicBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLocalMusicBinding.inflate(layoutInflater, container, false)

        binding.localPartBase.rvGroup.isVisible = false
        binding.localPartBase.refreshGroup.isVisible = false
        binding.localPartBase.progressBar.isVisible = false

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}