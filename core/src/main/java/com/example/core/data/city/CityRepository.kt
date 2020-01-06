package com.example.core.data.city

class CityRepository (private val cityDataSource: CityDataSource) {
    suspend fun getStoredCities() = cityDataSource.getStoredCities()
    suspend fun getRemoteCities(citySearchKeyWord: String): List<Any> = cityDataSource.getRemoteCities(citySearchKeyWord)
}