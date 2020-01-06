package com.example.weather.framework.di

import com.example.core.data.city.CityDataSource
import com.example.core.data.weather.WeatherDataSource
import com.example.weather.framework.network.CityDataSourceImpl
import com.example.weather.framework.network.WeatherDataSourceImpl
import org.koin.core.module.Module
import org.koin.dsl.module

val dataSourceModule: Module = module {
    single { CityDataSourceImpl(get(), get()) as CityDataSource }
    single { WeatherDataSourceImpl(get(), get()) as WeatherDataSource }
}