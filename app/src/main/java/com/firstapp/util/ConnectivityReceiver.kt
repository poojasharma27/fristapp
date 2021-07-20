package com.firstapp.util

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log


open class ConnectivityReceiver : BroadcastReceiver() {


    override fun onReceive(context: Context?, intent: Intent?) {
        if (isInternetAvailable(context)){
            showToastLong(context,"NetworkAvailable")
            Log.d("NetworkStatus","NetworkAvailable")
        }else{
            showToastLong(context,"NetworkNotAvailable")
            Log.d("NetworkStatus","NetworkNotAvailable")

        }
    }

    /* companion object {
         lateinit var connectivityReceiverListener: ConnectivityReceiverListener
         fun isConnected(): Boolean {
             val connectivityManager = MyApplication.getAppContext().applicationContext
                 .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
             val activeNetwork = connectivityManager.activeNetwork
             connectivityManager.getNetworkCapabilities(activeNetwork)?.run {
                 return when {
                     hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                     hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                     hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                     else -> false
                 }
             }
             return false
         }
     }

     override fun onReceive(context: Context?, intent: Intent?) {
         val connectivityManager = context
             ?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
         val activeNetwork = connectivityManager.activeNetwork
         val isConnected: Boolean? = connectivityManager.getNetworkCapabilities(activeNetwork)?.run {
             when {
                 hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                 hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                 hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                 else -> false
             }
         }
         if (connectivityReceiverListener != null) {
             isConnected?.let { connectivityReceiverListener.onNetworkConnectionChanged(it) }
         }

     }


     interface ConnectivityReceiverListener {
         fun onNetworkConnectionChanged(isConnected: Boolean)
     }*/
}