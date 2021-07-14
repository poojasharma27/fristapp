package com.firstapp.util

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey

class SessionManagement(context: Context) {

     private var sharedPreference: SharedPreferences?= null


    init {
        val mainKey = MasterKey.Builder(context)
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
            .build()
        EncryptedSharedPreferences.create(
            context, // fileName,
            "",
            mainKey, // masterKeyAlias
            // context
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        ).also { sharedPreference = it }
    }

    fun setUserEmail(email: String?){
        setSharedPreference(ExtrasConstants.Email.name,email.toString())
    }
    fun getUserEmail():String?{
        return getSharedPreference(ExtrasConstants.Email.name)
    }


    fun setUserPassword(password:String){
        setSharedPreference(ExtrasConstants.Password.name,password)
    }

    fun getPassword(): String?{
        return getSharedPreference(ExtrasConstants.Password.name)
    }
    private fun getSharedPreference(key: String): String?{
        return sharedPreference?.getString(key,null)
    }


     private fun setSharedPreference(key:String, value:String) {
         val editor = sharedPreference?.edit()
         editor?.putString(key,value)
             editor?.apply()

     }

    fun clearSharedPreference(){
        val editor = sharedPreference?.edit()
        editor?.clear()
            editor?.apply()

    }
}