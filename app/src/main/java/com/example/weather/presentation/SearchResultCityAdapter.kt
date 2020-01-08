package com.example.weather.presentation

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.core.domain.City
import com.example.weather.helper.NavigationListener
import com.example.weather.onClick
import com.example.weather.toNameAndCountry

class SearchResultCityAdapter(
    context: Context,
    resource: Int,
    private val navigationListener: NavigationListener,
    private val cbItemClick: () -> Boolean
) :
    ArrayAdapter<City?>(context, resource) {
    private val mlistData = arrayListOf<City>()
    fun setData(list: List<City>) {
        mlistData.clear()
        mlistData.addAll(list)
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val textView = super.getView(position, convertView, parent) as TextView
        val city = getItem(position)
        textView.onClick {
            cbItemClick()
            navigationListener.goNext(city, true, addToBackStack = true)
        }
        textView.text = city.toNameAndCountry()
        return textView
    }

    override fun getCount(): Int {
        return mlistData.size
    }

    override fun getItem(position: Int): City {
        return mlistData[position]
    }
}