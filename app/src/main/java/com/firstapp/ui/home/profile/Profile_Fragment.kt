package com.firstapp.ui.home.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.firstapp.R

class Profile_Fragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v:View = inflater.inflate(R.layout.fragmnet_profile,container,false)
        return v.rootView
    }
}