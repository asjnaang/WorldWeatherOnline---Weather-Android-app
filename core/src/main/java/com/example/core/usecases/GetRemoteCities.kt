package com.example.core.usecases

import com.example.core.data.city.CityRepository

class GetRemoteCities(private val cityRepository: CityRepository) {
    suspend operator fun invoke(citySearchKeyWord: String) = cityRepository.getRemoteCities(citySearchKeyWord)
}