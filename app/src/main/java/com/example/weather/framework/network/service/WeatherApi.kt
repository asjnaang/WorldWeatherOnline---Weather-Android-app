package com.example.weather.framework.network.service

import com.example.weather.framework.network.model.WeatherResponse
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query

interface WeatherApi {

    @Headers("Content-Type: application/json")
    @POST("weather.ashx")
    suspend fun getWeatherByCity(
        @Query("key") key: String,
        @Query("q") latlongString: String,
        @Query("format") format: String = "json",
        @Query("fx") fx: String = "no",
        @Query("cc") cc: String = "yes",
        @Query("mca") mca: String = "no",
        @Query("fx24") fx24: String = "no"
    ): WeatherResponse
}