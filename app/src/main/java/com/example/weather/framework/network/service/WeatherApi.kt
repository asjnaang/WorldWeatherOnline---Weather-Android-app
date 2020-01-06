package com.example.weather.framework.network.service

import com.example.weather.framework.network.model.WeatherResponse
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface WeatherApi {

    @Headers("Content-Type: application/json")
    @POST("weather.ashx")
    suspend fun getWeatherByCity(
        @Query("key") key: String,
        @Query("q") latlongString: String,
        @Path("format") format: String = "json",
        @Path("fx") fx: String = "no",
        @Path("cc") cc: String = "yes",
        @Path("mca") mca: String = "no",
        @Path("fx24") fx24: String = "no"
    ): WeatherResponse
}