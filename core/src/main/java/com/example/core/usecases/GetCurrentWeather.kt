package com.example.core.usecases

import com.example.core.data.weather.WeatherRepository
import com.example.core.domain.Coordinates

class GetCurrentWeather (private val weatherRepository: WeatherRepository) {
    suspend operator fun invoke(coordinates: Coordinates) = weatherRepository.getCurrent(coordinates)
}