package com.example.core.domain

data class Weather(val imageUrl: String,
                   val humidity: String,
                   val desc: String,
                   val tempInC: String,
                   val uvIndex: String)