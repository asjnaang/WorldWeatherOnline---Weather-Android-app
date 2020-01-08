package com.example.weather.presentation.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.core.domain.City
import com.example.weather.Interactors
import com.example.weather.MainActivity
import com.example.weather.R
import com.example.weather.databinding.MainFragmentBinding
import com.example.weather.databinding.MainFragmentBindingImpl
import com.example.weather.presentation.ui.BaseFragment
import com.example.weather.presentation.ui.CityItemAdapter
import kotlinx.android.synthetic.main.main_fragment.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.android.ext.android.inject

class MainFragment : BaseFragment() {
    lateinit var viewAdapter: CityItemAdapter
    private val interactors: Interactors by inject()

    companion object {
        fun newInstance() =
            MainFragment()
    }

    private val viewModel: MainViewModel by inject()
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
        binding.viewModel = viewModel
        initCityItems()
    }

    fun refresh() {
        actionbarListener.updateTitle(getString(R.string.app_name))
        GlobalScope.launch {
            viewModel.cityItems.postValue(interactors.getStoredCities())
        }
    }

    private fun initCityItems() {
        val viewManager = LinearLayoutManager(mActivity)
        viewAdapter = CityItemAdapter(navigationListener = mActivity)
        city_items.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }
        viewModel.cityItems.observe(this, Observer {
            viewAdapter.update(it)
        })
    }
}
