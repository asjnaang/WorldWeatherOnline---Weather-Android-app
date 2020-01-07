package com.example.weather.helper

import com.example.core.domain.City

interface NavigationListener {
    fun goNext(city: City, store: Boolean = false)
}