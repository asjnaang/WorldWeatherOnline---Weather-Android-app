package com.example.weather.framework.di

import com.example.core.usecases.GetStoredCities
import com.example.core.usecases.GetCurrentWeather
import com.example.core.usecases.GetRemoteCities
import com.example.core.usecases.StoreCity
import org.koin.core.module.Module
import org.koin.dsl.module


val useCaseModule: Module = module {
    single { GetStoredCities(get()) }
    single { GetCurrentWeather(get()) }
    single { GetRemoteCities(get()) }
    single { StoreCity(get()) }
}