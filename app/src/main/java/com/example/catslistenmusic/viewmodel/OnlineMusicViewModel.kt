package com.example.catslistenmusic.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.catslistenmusic.model.Track
import com.example.catslistenmusic.model.api.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class OnlineMusicViewModel: ViewModel() {

    private val _chartData = MutableLiveData<List<Track>>()
    val chartData: LiveData<List<Track>> = _chartData

    fun fetchChartData() {
        viewModelScope.launch {
            try {
                val chartResponse = RetrofitClient.apiService.getChart()
                _chartData.postValue(chartResponse.tracks.data)
            }
            catch (e: Exception) {

            }
        }
    }
}