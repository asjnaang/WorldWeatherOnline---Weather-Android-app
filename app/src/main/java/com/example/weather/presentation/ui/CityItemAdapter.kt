package com.example.weather.presentation.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.core.domain.City
import com.example.weather.toNameAndCountry

class CityItemAdapter(private val cities: List<City> = emptyList()) :
    RecyclerView.Adapter<CityItemAdapter.ViewHolder>() {
    class ViewHolder(textView: TextView) : RecyclerView.ViewHolder(textView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val textView = LayoutInflater.from(parent.context)
            .inflate(android.R.layout.simple_list_item_1, parent, false) as TextView
        return ViewHolder(textView)
    }

    override fun getItemCount() = cities.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        (holder.itemView as TextView).text = cities[position].toNameAndCountry()
    }
}

