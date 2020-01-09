package com.example.weather

import android.app.SearchManager
import android.content.Context
import android.os.Handler
import android.os.Message
import android.text.Editable
import android.text.TextWatcher
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.SearchView.SearchAutoComplete
import androidx.core.os.bundleOf
import com.example.core.domain.City
import com.example.weather.helper.ActionbarListener
import com.example.weather.helper.NavigationListener
import com.example.weather.presentation.SearchResultCityAdapter
import com.example.weather.presentation.ui.BaseFragment
import com.example.weather.presentation.ui.city.CityFragment
import com.example.weather.presentation.ui.home.MainFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.android.ext.android.inject


abstract class ActionBarActivity : AppCompatActivity(), NavigationListener, ActionbarListener,
    SearchView.OnQueryTextListener {

    private lateinit var searchResultCityAdapter: SearchResultCityAdapter
    private lateinit var handler: Handler
    private val TRIGGER_AUTO_COMPLETE = 100
    private val AUTO_COMPLETE_DELAY: Long = 200
    private val interactors: Interactors by inject()
    lateinit var city: City

    /**
     * Init listener that looks for changes in Fragment back stack.
     */
    protected fun initBackStackListener() {
        supportFragmentManager.addOnBackStackChangedListener {
            val currentFragment = supportFragmentManager.findFragmentById(R.id.container)
            if (currentFragment?.javaClass?.name == MainFragment::class.java.name) {
                (currentFragment as MainFragment).refresh()
            }
        }
    }

    /**
     * Based on Query string, fetch City information
     */
    private fun fetchCityInfo(query: String?) {
        query?.let {
            if (it.length <= 2) return
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

    protected fun initHandler() {
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


    /**
     * Init SearchView from ActionBar
     */
    protected fun initSearchView(context: Context, searchMenuItem: MenuItem?) {
        val searchManager = context.getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = searchMenuItem?.actionView as SearchView
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        val searchAutoComplete: SearchAutoComplete =
            searchView?.findViewById(R.id.search_src_text)
        searchView.isSubmitButtonEnabled = false
        searchView.setOnQueryTextListener(this)
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
                triggerQuery(searchAutoComplete.text.toString())
            }

            override fun afterTextChanged(s: Editable) {}
        })
    }

    /**
     * Go to City Fragment with City as param
     */
    override fun goNext(city: City, store: Boolean, addToBackStack: Boolean) {
        if (store) {
            GlobalScope.launch {
                interactors.storeCity.invoke(city)
            }
        }
        val fragment = CityFragment.newInstance()
        fragment.arguments = bundleOf(AppConstants.CITY to city)
        navigateTo(fragment, addToBackStack)
    }

    /**
     * Replace current fragment if it already CityFragment.
     * If not addToBackStack and add to container.
     */
    protected fun navigateTo(fragment: BaseFragment, addToBackStack: Boolean = true) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        val currentFragment = supportFragmentManager.findFragmentById(R.id.container)
        if (addToBackStack && currentFragment?.javaClass?.name != CityFragment::class.java.name) {
            fragmentTransaction.addToBackStack(currentFragment?.javaClass?.name)
            fragmentTransaction.add(R.id.container, fragment)
        }
        fragmentTransaction
            .replace(R.id.container, fragment)
            .commit()
    }

    /**
     * Update ActionBar title as per Fragment changes.
     */
    override fun updateTitle(title: String) {
        supportActionBar?.title = title
    }

    /**
     * Since we have our own textChangedListener, no need this to work on top of it.
     */
    override fun onQueryTextSubmit(query: String?): Boolean {
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return true
    }

    private fun triggerQuery(query: String) {
        handler.removeMessages(TRIGGER_AUTO_COMPLETE)
        val message = Message()
        message.obj = query
        message.what = TRIGGER_AUTO_COMPLETE
        handler.sendMessageDelayed(message, AUTO_COMPLETE_DELAY)
    }
}

