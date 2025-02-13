package com.example.catslistenmusic.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.catslistenmusic.model.api.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class OnlineMusicViewModel: ViewModel() {

    private val _chartData = MutableLiveData<String>()
    val chartData: LiveData<String> = _chartData

    fun fetchChartData() {
        viewModelScope.launch {
            try {
                val chartResponse = RetrofitClient.apiService.getChart()
                _chartData.value = chartResponse.tracks.data.firstOrNull()!!.title

            } catch (e: Exception) {
                _chartData.value = e.printStackTrace().toString()
            }
        }
    }
}