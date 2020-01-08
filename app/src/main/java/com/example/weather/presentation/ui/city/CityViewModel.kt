package com.example.weather.presentation.ui.city

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import androidx.lifecycle.ViewModel
import com.bumptech.glide.Glide
import com.example.core.domain.Weather

class CityViewModel : ViewModel() {
    val temp: ObservableField<String> = ObservableField()
    val humidity: ObservableField<String> = ObservableField()
    val desc: ObservableField<String> = ObservableField()
    val weatherImageUrl: ObservableField<String> = ObservableField()
    val progressBarVisibility = ObservableInt(View.GONE)

    fun hideProgressBar() {
        progressBarVisibility.set(View.GONE)
    }

    fun showProgressbar() {
        progressBarVisibility.set(View.VISIBLE)
    }

    fun updateWeather(weather: Weather) {
        temp.set(weather.tempInC.toTemperatureInCelsius())
        humidity.set(weather.humidity.toHumidity())
        desc.set(weather.desc)
        weatherImageUrl.set(weather.imageUrl)
        hideProgressBar()
    }
}

private fun String.toHumidity(): String {
    return String.format("%s\uFE6A", this)
}

private fun String.toTemperatureInCelsius(): String {
    return String.format("%s\u2103", this)
}
