package com.firstapp.ui.home.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.firstapp.databinding.FragmnetProfileBinding

class ProfileFragment : Fragment() {
    private var binding:FragmnetProfileBinding?=null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding= FragmnetProfileBinding.inflate(inflater,container,false)
        return binding?.root
    }
}