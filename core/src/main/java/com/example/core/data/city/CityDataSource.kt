package com.example.core.data.city

import com.example.core.domain.City

interface CityDataSource {
    suspend fun getStoredCities(): List<City>
    suspend fun getRemoteCities(citySearchKeyWord: String): List<City>
    suspend fun storeCity(city: City)
}