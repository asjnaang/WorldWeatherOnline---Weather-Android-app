package com.example.weather.presentation.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.core.domain.City
import com.example.weather.helper.NavigationListener
import com.example.weather.onClick
import com.example.weather.toNameAndCountry

class CityItemAdapter(
    private val cities: MutableList<City> = mutableListOf(),
    private val navigationListener: NavigationListener
) :
    RecyclerView.Adapter<CityItemAdapter.ViewHolder>() {
    class ViewHolder(textView: TextView) : RecyclerView.ViewHolder(textView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val textView = LayoutInflater.from(parent.context)
            .inflate(android.R.layout.simple_list_item_1, parent, false) as TextView
        return ViewHolder(textView)
    }

    override fun getItemCount() = cities.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val view = (holder.itemView as TextView)
        val city = cities[position]
        view.text = city.toNameAndCountry()
        view.onClick {
            navigationListener.goNext(city, store = true)
        }
    }

    fun update(updatedCities: List<City>) {
        cities.clear()
        cities.addAll(updatedCities)
        notifyDataSetChanged()
    }
}

