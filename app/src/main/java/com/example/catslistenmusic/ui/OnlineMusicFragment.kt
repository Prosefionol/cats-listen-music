package com.example.catslistenmusic.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.catslistenmusic.R
import com.example.catslistenmusic.databinding.FragmentOnlineMusicBinding
import com.example.catslistenmusic.model.api.ErrorAnswerApi
import com.example.catslistenmusic.model.api.PendingAnswerApi
import com.example.catslistenmusic.model.api.SuccessAnswerApi
import com.example.catslistenmusic.model.api.getAnswer
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

        adapter = TrackAdapter(requireContext())
        binding.onlinePartBase.recyclerView.adapter = adapter
        binding.onlinePartBase.recyclerView.layoutManager = LinearLayoutManager(requireContext())

        viewModel.chartData.observe(viewLifecycleOwner) {
            when (it) {
                is PendingAnswerApi -> {
                    binding.onlinePartBase.rvGroup.isVisible = false
                    binding.onlinePartBase.progressBar.isVisible = true
                    binding.onlinePartBase.refreshGroup.isVisible = false
                }
                is ErrorAnswerApi -> {
                    binding.onlinePartBase.rvGroup.isVisible = false
                    binding.onlinePartBase.progressBar.isVisible = false
                    binding.onlinePartBase.refreshGroup.isVisible = true
                }
                is SuccessAnswerApi -> {
                    adapter.submitList(it.getAnswer()!!)
                    binding.onlinePartBase.rvGroup.isVisible = true
                    binding.onlinePartBase.progressBar.isVisible = false
                    binding.onlinePartBase.refreshGroup.isVisible = false
                }
            }
        }

        binding.onlinePartBase.refreshButton.setOnClickListener {
            viewModel.fetchChartData()
        }

        binding.onlinePartBase.searchButton.setOnClickListener {
            val query = binding.onlinePartBase.searchInput.text.toString()
            if (query.isNotEmpty()) {
                viewModel.fetchSearchData(query)
            }
            else {
                Toast.makeText(requireContext(), getString(R.string.search_hint), Toast.LENGTH_SHORT).show()
            }
        }

        viewModel.fetchChartData()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
