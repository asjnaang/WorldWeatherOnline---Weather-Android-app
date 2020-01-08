package com.example.weather.presentation.ui.home

import androidx.databinding.ObservableInt
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.example.core.domain.City
import com.example.weather.Interactors
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainViewModel(interactors: Interactors) : ViewModel() {
    val itemCount = ObservableInt(0)
    val cityItems = MediatorLiveData<List<City>>().apply {
        GlobalScope.launch {
            val cities = interactors.getStoredCities.invoke()
            itemCount.set(cities.size)
            postValue(cities)
        }
    }
}
