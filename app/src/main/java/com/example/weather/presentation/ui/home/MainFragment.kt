package com.example.weather.presentation.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.core.domain.City
import com.example.weather.Interactors
import com.example.weather.MainActivity
import com.example.weather.R
import com.example.weather.databinding.MainFragmentBinding
import com.example.weather.databinding.MainFragmentBindingImpl
import com.example.weather.presentation.ui.CityItemAdapter
import kotlinx.android.synthetic.main.main_fragment.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.android.ext.android.inject

class MainFragment : Fragment() {
    private val interactors: Interactors by inject()
    companion object {
        fun newInstance() =
            MainFragment()
    }

    private lateinit var viewModel: MainViewModel
    private lateinit var binding: MainFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MainFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        binding.viewModel = viewModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        GlobalScope.launch {
            val cities = interactors.getStoredCities.invoke()
            viewModel.itemCount = cities.size
            withContext(Dispatchers.Main) {
                initCityItems(cities)
            }
        }
    }

    private fun initCityItems(cities: List<City>) {
        val viewManager = LinearLayoutManager(activity)
        val viewAdapter = CityItemAdapter(cities, activity as MainActivity)
        city_items.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }
    }
}
