package com.example.catslistenmusic.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.catslistenmusic.model.Track
import com.example.catslistenmusic.model.api.AnswerApi
import com.example.catslistenmusic.model.api.ErrorAnswerApi
import com.example.catslistenmusic.model.api.PendingAnswerApi
import com.example.catslistenmusic.model.api.RetrofitClient
import com.example.catslistenmusic.model.api.SuccessAnswerApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

class OnlineMusicViewModel(
    private val savedStateHandle: SavedStateHandle
): ViewModel() {

    private val _trackData = savedStateHandle.getLiveData<AnswerApi<List<Track>>>(KEY_STATE)
    val trackData: LiveData<AnswerApi<List<Track>>> = _trackData

    fun fetchChartData() {
        _trackData.postValue(PendingAnswerApi())
        viewModelScope.launch {
            try {
                val chartResponse = withContext(Dispatchers.IO) {
                    RetrofitClient.apiService.getChart()
                }
                _trackData.postValue(SuccessAnswerApi(chartResponse.tracks.data))
            }
            catch (e: Exception) {
                _trackData.postValue(ErrorAnswerApi(e))
            }
        }
    }

    fun fetchSearchData(query: String) {
        val encodedQuery = URLEncoder.encode(query, StandardCharsets.UTF_8.toString())
        _trackData.postValue(PendingAnswerApi())
        viewModelScope.launch {
            try {
                val searchResponse = withContext(Dispatchers.IO) {
                    RetrofitClient.apiService.search(encodedQuery)
                }
                _trackData.postValue(SuccessAnswerApi(searchResponse.data))
            }
            catch (e: Exception) {
                _trackData.postValue(ErrorAnswerApi(e))
            }
        }
    }

    companion object {
        const val KEY_STATE = "state"
    }
}
