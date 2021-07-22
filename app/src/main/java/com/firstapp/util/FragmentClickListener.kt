package com.firstapp.util

import android.view.View
import androidx.fragment.app.Fragment

interface FragmentClickListener {
    fun onFragmentTransaction(fragment: Fragment)
}