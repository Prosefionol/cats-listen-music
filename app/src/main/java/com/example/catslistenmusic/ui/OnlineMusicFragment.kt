package com.example.catslistenmusic.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.example.catslistenmusic.databinding.FragmentOnlineMusicBinding

class OnlineMusicFragment : Fragment() {

    private var _binding: FragmentOnlineMusicBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOnlineMusicBinding.inflate(layoutInflater, container, false)

        binding.onlinePartBase.rvGroup.isVisible = false
        binding.onlinePartBase.refreshGroup.isVisible = false

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
