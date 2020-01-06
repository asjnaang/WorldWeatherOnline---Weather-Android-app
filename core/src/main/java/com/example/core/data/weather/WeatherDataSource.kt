package com.example.core.data.weather

import com.example.core.domain.Coordinates
import com.example.core.domain.Weather

interface WeatherDataSource {
    suspend fun getCurrent(coordinates: Coordinates): Weather
}