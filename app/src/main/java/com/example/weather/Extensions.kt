package com.example.weather

import com.example.core.domain.City

fun String?.filterEmpty(defaultValue: String = ""): String {
    return this ?: defaultValue
}

fun City?.toNameAndCountry(): String? {
    return this?.name.plus(", ").plus(this?.country)
}