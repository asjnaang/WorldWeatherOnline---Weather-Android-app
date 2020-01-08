package com.example.weather.presentation.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.weather.MainActivity
import com.example.weather.helper.ActionbarListener
import com.example.weather.helper.NavigationListener

abstract class BaseFragment : Fragment() {
    lateinit var mActivity: MainActivity
    lateinit var actionbarListener: ActionbarListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mActivity = (activity as MainActivity)
        actionbarListener = mActivity
    }
}

