package com.example.core.data.weather

import com.example.core.domain.Coordinates
import com.example.core.domain.Weather


class WeatherRepository(private val dataSource: WeatherDataSource) {
    suspend fun getCurrent(coordinates: Coordinates): Weather =
        dataSource.getCurrent(coordinates)
}