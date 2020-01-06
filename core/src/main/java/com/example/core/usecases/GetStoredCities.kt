package com.example.core.usecases

import com.example.core.data.city.CityRepository

class GetStoredCities(private val cityRepository: CityRepository) {
    suspend operator fun invoke() = cityRepository.getStoredCities()
}