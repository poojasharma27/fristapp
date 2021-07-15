package com.firstapp.util

import android.content.Context
import android.net.ConnectivityManager
import android.widget.Toast

fun showToastLong(context: Context?, msg: String) {
    context?.let {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
    }

}

fun showToastShort(context: Context, msg: String) {
    Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
}

/*
fun isInternetAvailable(context: Context?): Boolean {
    val connectivityManager = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetwork = connectivityManager.activeNetwork
    connectivityManager.getNetworkCapabilities(activeNetwork)?.run {
        when {
            hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }
    }
    return false
}
*/


fun isInternetAvailable(context: Context?):Boolean{
    val connectivityManager =context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetworkInfo = connectivityManager.activeNetworkInfo
    return activeNetworkInfo != null && activeNetworkInfo.isConnected
}