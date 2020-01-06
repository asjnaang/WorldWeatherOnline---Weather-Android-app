package com.example.weather.framework.di

import com.example.weather.BuildConfig
import com.example.weather.framework.network.service.CityApi
import com.example.weather.framework.network.service.WeatherApi
import com.example.weather.framework.network.createNetworkClient
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit

val networkModule: Module = module {
    single { cityApi }
    single { weatherApi }
}

private const val BASE_URL = "https://api.worldweatheronline.com/premium/v1/"
private val retrofit: Retrofit = createNetworkClient(BASE_URL, BuildConfig.DEBUG)

private val cityApi: CityApi = retrofit.create(
    CityApi::class.java)
private val weatherApi: WeatherApi = retrofit.create(
    WeatherApi::class.java)