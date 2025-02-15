package com.example.catslistenmusic.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.catslistenmusic.model.Track
import com.example.catslistenmusic.model.api.AnswerApi
import com.example.catslistenmusic.model.api.ErrorAnswerApi
import com.example.catslistenmusic.model.api.PendingAnswerApi
import com.example.catslistenmusic.model.api.RetrofitClient
import com.example.catslistenmusic.model.api.SuccessAnswerApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

class OnlineMusicViewModel: ViewModel() {

    private val _chartData = MutableLiveData<AnswerApi<List<Track>>>(PendingAnswerApi())
    val chartData: LiveData<AnswerApi<List<Track>>> = _chartData

    fun fetchChartData() {
        _chartData.postValue(PendingAnswerApi())
        viewModelScope.launch {
            try {
                val chartResponse = RetrofitClient.apiService.getChart()
                _chartData.postValue(SuccessAnswerApi(chartResponse.tracks.data))
            }
            catch (e: Exception) {
                _chartData.postValue(ErrorAnswerApi(e))
            }
        }
    }

    fun fetchSearchData(query: String) {
        val encodedQuery = URLEncoder.encode(query, StandardCharsets.UTF_8.toString())
        _chartData.postValue(PendingAnswerApi())
        viewModelScope.launch {
            try {
                val searchResponse = RetrofitClient.apiService.search(encodedQuery)
                _chartData.postValue(SuccessAnswerApi(searchResponse.data))
            }
            catch (e: Exception) {
                _chartData.postValue(ErrorAnswerApi(e))
            }
        }
    }
}