package com.firstapp.util

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import androidx.security.crypto.MasterKeys

class SessionManagement {

    var sharedPreference: SharedPreferences?= null
    val masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)


  /*  constructor(context: Context){
        sharedPreference = context.getSharedPreferences("db",Context.MODE_PRIVATE)
    }*/


  constructor(context: Context){
      /**
       * TODO  update MasterKey
       */
      //val masterKeyAliass = MasterKey.Builder(context)
     sharedPreference   =   EncryptedSharedPreferences.create(
            "encrypted_preferences", // fileName
            masterKeyAlias, // masterKeyAlias
            context, // context
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV, // prefKeyEncryptionScheme
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM // prefvalueEncryptionScheme
        )

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
         val editor = sharedPreference!!.edit()
         editor.putString(key,value)
         editor.apply()
     }

    fun clearSession(){
        val editor = sharedPreference?.edit()
        editor?.clear()
        editor?.apply()
    }
}