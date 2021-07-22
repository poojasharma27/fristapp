package com.firstapp.util

import android.view.View
import androidx.fragment.app.Fragment
import com.firstapp.network.model.Article

interface ItemClickListener {

    fun onViewClicked(view: View, position : Int)

    fun OnSaveClicked(view: View,article: Article)


}

