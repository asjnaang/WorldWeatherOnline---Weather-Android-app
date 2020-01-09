package com.example.weather.presentation.ui.city

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.core.domain.City
import com.example.core.domain.Coordinates
import com.example.weather.AppConstants
import com.example.weather.Interactors
import com.example.weather.databinding.CityFragmentBinding
import com.example.weather.presentation.ui.BaseFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.android.ext.android.inject

class CityFragment : BaseFragment() {
    private lateinit var city: City
    private val interactors: Interactors by inject()
    companion object {
        fun newInstance() =
            CityFragment()
    }

    private val viewModel: CityViewModel by inject()
    private lateinit var binding: CityFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = CityFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.viewModel = viewModel
        city = arguments?.get(AppConstants.CITY) as City
    }

    override fun onResume() {
        super.onResume()
        actionbarListener.updateTitle(city.name)
        viewModel.showProgressbar()
        GlobalScope.launch {
            val weather = interactors.getCurrentWeather(
                Coordinates(
                    city.latitude.toDouble(),
                    city.longitude.toDouble()
                )
            )
            weather?.let {
                withContext(Dispatchers.Main) {
                    viewModel.updateWeather(weather)
                }
            }
        }
    }
}

