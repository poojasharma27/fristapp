package com.firstapp.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService

fun showToastLong(context: Context?, msg: String) {
    context?.let {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
    }

}

fun showToastShort(context: Context, msg: String) {
    Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
}

/*fun isInternetAvailable(context: Context): Boolean {
    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetworkInfo = connectivityManager.activeNetwork
    return activeNetworkInfo != null && activeNetworkInfo.isConnected
}*/


/*private fun isNetworkAvailable(context: Context): Boolean {
    val connectivityManager =context.getSystemService(Context.CONNECTIVITY_SERVICE)
    return if (connectivityManager is ConnectivityManager) {
        val networkInfo = connectivityManager.activeNetwork
        networkInfo?. ?: false
    } else false
}*/