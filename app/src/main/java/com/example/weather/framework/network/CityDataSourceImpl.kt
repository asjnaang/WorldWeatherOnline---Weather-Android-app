package com.example.weather.framework.network

import android.content.Context
import com.example.core.data.city.CityDataSource
import com.example.core.domain.City
import com.example.weather.R
import com.example.weather.framework.db.CityEntity
import com.example.weather.framework.db.WeatherDatabase
import com.example.weather.framework.network.model.mapToDomain
import com.example.weather.framework.network.service.CityApi

class CityDataSourceImpl(private val context: Context, private val cityApi: CityApi) :
    CityDataSource {
    private val cityDao = WeatherDatabase.getInstance(context).cityDao()
    override suspend fun getStoredCities(): List<City> {
        return cityDao.getCities().map { City(it.name, it.country,  it.latitude, it.longitude) }
    }

    override suspend fun getRemoteCities(citySearchKeyWord: String): List<City> {
        return cityApi.getRemoteCities(
            key = context.getString(R.string.api_key),
            queryString = citySearchKeyWord
        )?.mapToDomain()
    }

    override suspend fun storeCity(city: City) {
        cityDao.addCity(
            CityEntity(
                name = city.name,
                country = city.country,
                latitude = city.latitude,
                longitude = city.longitude
            )
        )
    }
}