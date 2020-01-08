package com.example.weather

import android.os.Bundle
import android.view.Menu
import com.example.weather.presentation.ui.home.MainFragment


class MainActivity : ActionBarActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            goToMainFragment()
        }
        initBackStackListener()
    }

    private fun goToMainFragment() {
        navigateTo(MainFragment.newInstance(), addToBackStack = false)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_main, menu)
        val searchMenuItem = menu?.findItem(R.id.action_bar_search)
        initSearchView(this, searchMenuItem)
        initHandler()
        return true
    }
}
