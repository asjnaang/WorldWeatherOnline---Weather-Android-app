package com.example.core.data.weather

import com.example.core.domain.Coordinates
import com.example.core.domain.Weather


class WeatherRepository(private val dataSource: WeatherDataSource) {
    suspend fun getCurrentWeather(coordinates: Coordinates): Weather =
        dataSource.getCurrentWeather(coordinates)
}