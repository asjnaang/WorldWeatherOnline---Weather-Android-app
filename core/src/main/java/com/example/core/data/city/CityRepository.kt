package com.example.core.data.city

import com.example.core.domain.City

class CityRepository (private val cityDataSource: CityDataSource) {
    suspend fun getStoredCities() = cityDataSource.getStoredCities()
    suspend fun getRemoteCities(citySearchKeyWord: String): List<City> = cityDataSource.getRemoteCities(citySearchKeyWord)
    suspend fun storyCity(city: City) = cityDataSource.storeCity(city)
}