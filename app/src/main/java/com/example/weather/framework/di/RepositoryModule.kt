package com.example.weather.framework.di

import com.example.core.data.city.CityRepository
import com.example.core.data.weather.WeatherRepository
import org.koin.core.module.Module
import org.koin.dsl.module


val repositoryModule: Module = module {
    single { CityRepository(get()) }
    single { WeatherRepository(get()) }
}