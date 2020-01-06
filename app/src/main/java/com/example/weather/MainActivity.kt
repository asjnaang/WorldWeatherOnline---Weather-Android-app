package com.example.weather

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import com.example.weather.presentation.ui.home.MainFragment


class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener {

    lateinit var searchView: SearchView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_main, menu)
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchMenuItem = menu?.findItem(R.id.action_bar_search)
        searchView = searchMenuItem?.actionView as SearchView
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.setOnQueryTextListener(this)
        return true

    }

    override fun onQueryTextSubmit(query: String?): Boolean {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        return true
    }


}
