package com.example.core.domain

import java.io.Serializable

data class City(val name: String,
                val country: String,
                val latitude: String,
                val longitude: String): Serializable
