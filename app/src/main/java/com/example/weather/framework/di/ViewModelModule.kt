package com.example.weather.framework.di

import com.example.weather.presentation.ui.city.CityViewModel
import com.example.weather.presentation.ui.home.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module


val viewModelModule: Module = module {
    viewModel { MainViewModel() }
    viewModel { CityViewModel() }
}