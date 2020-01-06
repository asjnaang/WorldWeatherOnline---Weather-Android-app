package com.example.weather.framework.di

import com.example.weather.Interactors
import org.koin.core.module.Module
import org.koin.dsl.module


val appModule: Module = module {
    single {  Interactors(get(), get(), get()) }
}