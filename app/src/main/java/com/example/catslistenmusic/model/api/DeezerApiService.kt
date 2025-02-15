package com.example.catslistenmusic.model.api

import com.example.catslistenmusic.model.ChartResponse
import com.example.catslistenmusic.model.TrackData
import retrofit2.http.GET
import retrofit2.http.Query

interface DeezerApiService {
    @GET("chart")
    suspend fun getChart(): ChartResponse

    @GET("search")
    suspend fun search(@Query("q") query: String): TrackData
}
