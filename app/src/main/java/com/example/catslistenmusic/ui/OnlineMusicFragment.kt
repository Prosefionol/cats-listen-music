package com.example.catslistenmusic.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.catslistenmusic.databinding.FragmentOnlineMusicBinding
import com.example.catslistenmusic.ui.adapters.TrackAdapter
import com.example.catslistenmusic.viewmodel.OnlineMusicViewModel

class OnlineMusicFragment : Fragment() {

    private var _binding: FragmentOnlineMusicBinding? = null
    private val binding
        get() = _binding!!

    private val viewModel: OnlineMusicViewModel by viewModels()

    private lateinit var adapter: TrackAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOnlineMusicBinding.inflate(layoutInflater, container, false)

        binding.onlinePartBase.rvGroup.isVisible = false
        binding.onlinePartBase.refreshGroup.isVisible = false

        adapter = TrackAdapter(requireContext())
        binding.onlinePartBase.recyclerView.adapter = adapter
        binding.onlinePartBase.recyclerView.layoutManager = LinearLayoutManager(requireContext())

        viewModel.chartData.observe(viewLifecycleOwner) {
            adapter.submitList(it)
            binding.onlinePartBase.rvGroup.isVisible = true
            binding.onlinePartBase.progressBar.isVisible = false
        }

        binding.onlinePartBase.refreshButton.setOnClickListener {
            viewModel.fetchChartData()
        }

        viewModel.fetchChartData()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
