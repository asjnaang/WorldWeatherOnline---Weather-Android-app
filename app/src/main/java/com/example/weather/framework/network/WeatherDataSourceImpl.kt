package com.example.weather.framework.network

import android.content.Context
import com.example.core.data.city.CityDataSource
import com.example.core.data.weather.WeatherDataSource
import com.example.core.domain.City
import com.example.core.domain.Coordinates
import com.example.core.domain.Weather
import com.example.weather.R
import com.example.weather.framework.db.WeatherDatabase
import com.example.weather.framework.network.service.CityApi
import com.example.weather.framework.network.model.mapToDomain
import com.example.weather.framework.network.service.WeatherApi

class WeatherDataSourceImpl(private val context: Context, private val weatherApi: WeatherApi) :
    WeatherDataSource {
    override suspend fun getCurrentWeather(coordinates: Coordinates): Weather {
        return weatherApi.getWeatherByCity(
            key = context.getString(R.string.api_key),
            latlongString = coordinates.lat.toString().plus(",").plus(coordinates.lon)
        )?.mapToDomain()
    }
}