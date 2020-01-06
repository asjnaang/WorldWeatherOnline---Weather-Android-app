package com.example.weather.framework.network.model

import com.example.core.domain.City
import com.google.gson.annotations.SerializedName

data class CityResponse(
    @SerializedName("search_api") val search_api: SearchApi
)

data class SearchApi(
    @SerializedName("result") val result: List<Result>
)

data class AreaName(
    @SerializedName("value") val value: String
)

data class Country(
    @SerializedName("value") val value: String
)

data class Result(
    @SerializedName("areaName") val areaName: List<AreaName>,
    @SerializedName("country") val country: List<Country>,
    @SerializedName("latitude") val latitude: Double,
    @SerializedName("longitude") val longitude: Double
)

fun Result.mapToDomain(): City =
    City(areaName[0].value, country[0].value, latitude.toString(), longitude.toString())

fun CityResponse.mapToDomain(): List<City> = search_api?.let { search_api ->
    search_api.result.map { it.mapToDomain() }
}




