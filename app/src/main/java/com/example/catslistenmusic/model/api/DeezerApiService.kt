package com.example.catslistenmusic.model.api

import com.example.catslistenmusic.model.ChartResponse
import retrofit2.http.GET
import retrofit2.http.Headers

interface DeezerApiService {

    @GET("chart")
    suspend fun getChart(): ChartResponse
}