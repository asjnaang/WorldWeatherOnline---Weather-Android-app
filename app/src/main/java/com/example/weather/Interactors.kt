package com.example.weather

import com.example.core.usecases.GetStoredCities
import com.example.core.usecases.GetCurrentWeather
import com.example.core.usecases.GetRemoteCities

class Interactors(
    val getStoredCities: GetStoredCities,
    val getCurrentWeather: GetCurrentWeather,
    val getRemoteCities: GetRemoteCities
)