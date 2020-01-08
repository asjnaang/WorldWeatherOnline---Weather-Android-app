package com.example.weather.framework.network.model

import com.example.core.domain.Weather
import com.google.gson.annotations.SerializedName


data class WeatherResponse(
    @SerializedName("data") val data: Data
)

data class Data(
    @SerializedName("current_condition") val current_condition: List<CurrentCondition>
)

data class WeatherIconUrl(
    @SerializedName("value") val value: String
)

data class WeatherDesc(
    @SerializedName("value") val value: String
)

data class CurrentCondition(
    @SerializedName("temp_C") val tempInC: Int,
    @SerializedName("weatherIconUrl") val weatherIconUrl: List<WeatherIconUrl>,
    @SerializedName("weatherDesc") val desc: List<WeatherDesc>,
    @SerializedName("humidity") val humidity: Int,
    @SerializedName("uvIndex") val uvIndex: Int
)

fun CurrentCondition.mapToDomain(): Weather = Weather(
    weatherIconUrl[0].value,
    humidity.toString(),
    desc[0].value,
    tempInC.toString(),
    uvIndex.toString()
)

fun WeatherResponse.mapToDomain(): Weather =
    data.current_condition?.let { data.current_condition.map { it.mapToDomain() }[0] }
