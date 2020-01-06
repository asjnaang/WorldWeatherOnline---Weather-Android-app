package com.example.weather.framework.di

import org.koin.core.context.loadKoinModules

fun injectFeature() = loadFeature

private val loadFeature by lazy {
    loadKoinModules(
        listOf(
            appModule,
            dataSourceModule,
            networkModule,
            repositoryModule,
            useCaseModule,
            viewModelModule
        )
    )
}
