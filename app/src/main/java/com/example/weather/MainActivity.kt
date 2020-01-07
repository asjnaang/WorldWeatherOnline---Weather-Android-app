package com.example.weather

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.text.Editable
import android.text.TextWatcher
import android.view.Menu
import android.view.MenuItem
import android.widget.AdapterView.OnItemClickListener
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.SearchView.SearchAutoComplete
import androidx.core.os.bundleOf
import com.example.core.domain.City
import com.example.weather.helper.NavigationListener
import com.example.weather.presentation.SearchResultCityAdapter
import com.example.weather.presentation.ui.city.CityFragment
import com.example.weather.presentation.ui.home.MainFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.android.ext.android.inject


class MainActivity : AppCompatActivity(), NavigationListener {

    private lateinit var searchResultCityAdapter: SearchResultCityAdapter
    lateinit var handler: Handler
    private val TRIGGER_AUTO_COMPLETE = 100
    private val AUTO_COMPLETE_DELAY: Long = 300
    private val interactors: Interactors by inject()
    lateinit var city: City

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            goToMainFragment()
        }
    }

    private fun goToMainFragment() {
        supportFragmentManager.beginTransaction()
            .add(R.id.container, MainFragment.newInstance())
            .commit()
    }

    private fun fetchCityInfo(query: String?) {
        query?.let {
            if (it.length <= 3) return
            GlobalScope.launch {
                val suggestions = interactors.getRemoteCities.invoke(query.filterEmpty())
                suggestions?.let {
                    withContext(Dispatchers.Main) {
                        searchResultCityAdapter.setData(suggestions)
                        searchResultCityAdapter.notifyDataSetChanged()
                    }
                }
            }
        }
    }

    private fun initHandler() {
        handler = Handler(Handler.Callback { msg ->
            if (msg.what == TRIGGER_AUTO_COMPLETE) {
                val text = msg.obj?.toString()
                if (!text.isNullOrBlank()) {
                    fetchCityInfo(text)
                }
            }
            false
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_main, menu)
        val searchMenuItem = menu?.findItem(R.id.action_bar_search)
        initSearchView(this, searchMenuItem)
        initHandler()
        return true
    }

    private fun initSearchView(context: Context, searchMenuItem: MenuItem?) {
        val searchManager = context.getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = searchMenuItem?.actionView as SearchView
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        val searchAutoComplete: SearchAutoComplete =
            searchView.findViewById(R.id.search_src_text)
        searchAutoComplete.setDropDownBackgroundResource(android.R.color.background_light)
        searchResultCityAdapter = SearchResultCityAdapter(
            context,
            android.R.layout.simple_dropdown_item_1line, this
        ) {
            searchMenuItem.collapseActionView()
        }
        searchAutoComplete.setAdapter(searchResultCityAdapter)
        searchAutoComplete.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                s: CharSequence,
                start: Int,
                count: Int,
                after: Int
            ) {
            }

            override fun onTextChanged(
                s: CharSequence,
                start: Int,
                before: Int,
                count: Int
            ) {
                handler.removeMessages(TRIGGER_AUTO_COMPLETE)
                val message = Message()
                message.obj = searchAutoComplete.text
                message.what = TRIGGER_AUTO_COMPLETE
                handler.sendMessageDelayed(message, AUTO_COMPLETE_DELAY)
            }

            override fun afterTextChanged(s: Editable) {}
        })
    }

    override fun goNext(city: City, store: Boolean) {
        if (store) {
            GlobalScope.launch {
                interactors.storeCity.invoke(city)
            }
        }
        val fragment = CityFragment.newInstance()
        fragment.arguments = bundleOf(AppConstants.CITY to city)
        supportFragmentManager.beginTransaction()
            .add(R.id.main, fragment)
            .addToBackStack(this.javaClass.name)
            .commit()
    }
}

