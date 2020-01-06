package com.example.weather.framework.network.service

import com.example.weather.framework.network.model.CityResponse
import retrofit2.http.*

interface CityApi {
    @GET("search.ashx")
    suspend fun getRemoteCities(
        @Query("key") key: String,
        @Query("q") queryString: String,
        @Query("format") format: String = "json"
    ): CityResponse
}