package com.example.catslistenmusic.model.api

import com.example.catslistenmusic.model.ChartResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface DeezerApiService {
    @Headers("Accept: application/json")
    @GET("chart")
    suspend fun getChart(): ChartResponse
}