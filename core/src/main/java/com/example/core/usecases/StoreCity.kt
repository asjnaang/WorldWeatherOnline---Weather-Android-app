package com.example.core.usecases

import com.example.core.data.city.CityRepository
import com.example.core.domain.City

class StoreCity(private val cityRepository: CityRepository) {
    suspend operator fun invoke(city: City) = cityRepository.storyCity(city)
}