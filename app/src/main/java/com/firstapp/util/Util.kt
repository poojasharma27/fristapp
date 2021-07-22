package com.firstapp.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.view.isGone
import com.firstapp.R


fun showToastLong(context: Context?, msg: String) {
    context?.let {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
    }

}

fun showToastShort(context: Context, msg: String) {
    Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
}



fun isInternetAvailable(context: Context?): Boolean {
    if(Build.VERSION.SDK_INT>Build.VERSION_CODES.Q){
        val connectivityManager =context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        return connectivityManager!!.activeNetworkInfo != null && connectivityManager!!.activeNetworkInfo!!.isConnected
    }else{
        val connectivityManager = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = connectivityManager.activeNetwork
        connectivityManager.getNetworkCapabilities(activeNetwork)?.run {
            return  when {
                hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                else -> false
            }
        }
    }


    return false
}



/*fun isInternetAvailable(context: Context?):Boolean{
    val connectivityManager =context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetworkInfo = connectivityManager.activeNetworkInfo
    return activeNetworkInfo != null && activeNetworkInfo.isConnected
}*/
