package com.example.catslistenmusic.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.example.catslistenmusic.databinding.FragmentOnlineMusicBinding
import com.example.catslistenmusic.viewmodel.OnlineMusicViewModel

class OnlineMusicFragment : Fragment() {

    private var _binding: FragmentOnlineMusicBinding? = null
    private val binding
        get() = _binding!!

    private val viewModel: OnlineMusicViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOnlineMusicBinding.inflate(layoutInflater, container, false)

        binding.onlinePartBase.rvGroup.isVisible = false
        binding.onlinePartBase.progressBar.isVisible = false

        viewModel.chartData.observe(viewLifecycleOwner) {
            binding.onlinePartBase.refreshTv.text = it
        }

        binding.onlinePartBase.refreshButton.setOnClickListener {
            viewModel.fetchChartData()
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
